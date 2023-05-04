package com.bilgeadam.service;

import com.bilgeadam.dto.request.ChangeStatusRequestDto;
import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.AuthRegisterResponseDto;
import com.bilgeadam.exception.AuthServiceException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.rabbitmq.model.AddAuthIdModel;
import com.bilgeadam.rabbitmq.model.CreatePersonMailModel;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.rabbitmq.producer.AuthIdProducer;
import com.bilgeadam.rabbitmq.producer.ChangeStatusProducer;
import com.bilgeadam.rabbitmq.producer.PasswordMailProducer;
import com.bilgeadam.rabbitmq.producer.RegisterProducer;
import com.bilgeadam.repository.IAuthRepository;
import com.bilgeadam.repository.entity.Auth;
import com.bilgeadam.repository.enums.ERole;
import com.bilgeadam.utility.CodeGenerator;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bilgeadam.repository.enums.EStatus.ACTIVE;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager tokenManager;
    private final RegisterProducer registerProducer;
    private final ChangeStatusProducer changeStatusProducer;
    private final AuthIdProducer authIdProducer;
    private final PasswordMailProducer passwordMailProducer;


    public AuthService(IAuthRepository repository, JwtTokenManager tokenManager, RegisterProducer registerProducer, ChangeStatusProducer changeStatusProducer, AuthIdProducer authIdProducer, PasswordMailProducer passwordMailProducer) {
        super(repository);
        this.repository = repository;
        this.tokenManager = tokenManager;
        this.registerProducer = registerProducer;

        this.changeStatusProducer = changeStatusProducer;
        this.authIdProducer = authIdProducer;
        this.passwordMailProducer = passwordMailProducer;
    }

    public AuthRegisterResponseDto register(RegisterRequestDto dto,String adminPassword) {
        if(!adminPassword.equals("admin1234")) {
            throw new AuthServiceException(EErrorType.LOGIN_ERROR_ADMIN);
        }
            if (repository.isEmail(dto.getEmail()))
                throw new AuthServiceException(EErrorType.REGISTER_ERROR_EMAIL);
            Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
            /**
             * Repo -> repository.save(auth); bu bana kaydettiği entityi döner
             * Servi -> save(auth); bu da bana kaydettiği entityi döner
             * direkt -> auth, bir şekilde kayıt edilen entity nin bilgileri istenir ve bunu döner.
             */
//        return repository.save(auth);
//            auth.setActivationCode(CodeGenerator.generateCode());
            auth.setRole(ERole.ADMIN);
            auth.setStatus(ACTIVE);
            save(auth);
            registerProducer.sendNewUser(IAuthMapper.INSTANCE.toRegisterModel(auth));
//        iUserProfileManager.save(IAuthMapper.INSTANCE.fromAuth(auth));
//        createUserProducer.convertAndSend(SaveAuthModel.builder()
//                        .authid(auth.getId())
//                        .email(auth.getEmail())
//                        .email(auth.getEmail())
//                .build());
            AuthRegisterResponseDto authRegisterResponseDto = IAuthMapper.INSTANCE.toAuthResponseDto(auth);

        return authRegisterResponseDto;


    }

    public Optional<Auth> findOptionalByEmailAndPassword(String email, String password) {
        return repository.findOptionalByEmailAndPassword(email,password);
    }

    /**
     * Kullanıcıyı doğrulayacak ve kullanıcının sistemi içinde hareket edebilmesi için
     * ona özel bir kimlik verecek.
     * Token -> JWT token
     * @param dto
     * @return
     */
    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth = repository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
//        if (!auth.get().getStatus().equals(ACTIVE))
//            auth.get().setRole(ERole.PERSONNEL);
//            auth.get().setStatus(ACTIVE);
//            throw new AuthServiceException(EErrorType.NOT_ACTIVE_ACCOUNT);
        return tokenManager.createToken(auth.get().getId()).get();
    }

    public Boolean changeStatus(ChangeStatusRequestDto dto) {
        Optional<Auth> auth = repository.findById(dto.getId());
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        if(dto.getActivationCode().equals(auth.get().getActivationCode())) {
            auth.get().setStatus(dto.getStatus());
            update(auth.get());
            changeStatusProducer.changeStatusUser(IAuthMapper.INSTANCE.toActivationModel(auth.get()));
            return true;
        } else {
            throw new AuthServiceException(EErrorType.ACTIVATE_CODE_ERROR);
        }
    }
    public List<Auth> findAll (String token) {
        Optional<Long> id = tokenManager.getIdFromToken(token);
        if (id.isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        if (findById(id.get()).isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        return findAll();
    }

    public Boolean createPerson(CreatePersonModel model) {
        Optional<Auth> cM = repository.findOptionalByEmail(model.getEmail());
        if(cM.isPresent())
            return false;
            //throw new AuthServiceException(EErrorType.REGISTER_ERROR_EMAIL);
        try {
            Auth auth = IAuthMapper.INSTANCE.toAuth(model);
            save(auth);
            Optional<Auth> auth2 = repository.findOptionalByEmail(model.getEmail());

            AddAuthIdModel addAuthIdModel = AddAuthIdModel.builder()
                    .authId(auth2.get().getId())
                    .email(auth.getEmail())
                    .eRole(auth.getRole())
                    .build();
            System.out.println("**************************");
            System.out.println(auth.getId());
            System.out.println(addAuthIdModel.getAuthId());
            System.out.println(addAuthIdModel.getEmail());
            authIdProducer.sendAuthId(addAuthIdModel);
            return true;
        } catch (Exception e) {
            throw new AuthServiceException(EErrorType.CREATE_ERROR);
        }
    }
    public Boolean changePassword(String email) {
        Optional<Auth> auth = repository.findOptionalByEmail(email);
        if(auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        auth.get().setPassword(CodeGenerator.generateCode());
        update(auth.get());

        CreatePersonMailModel mailModel = CreatePersonMailModel.builder()
                .email(email)
                .password(auth.get().getPassword())
                .build();
        passwordMailProducer.sendRepassword(mailModel);
        return true;
    }
}
