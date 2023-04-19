package com.bilgeadam.service;

import com.bilgeadam.dto.request.ChangeStatusRequestDto;
import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.AuthRegisterResponseDto;
import com.bilgeadam.exception.AuthServiceException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.rabbitmq.producer.ChangeStatusProducer;
import com.bilgeadam.rabbitmq.producer.RegisterProducer;
import com.bilgeadam.repository.IAuthRepository;
import com.bilgeadam.repository.entity.Auth;
import com.bilgeadam.utility.CodeGenerator;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager tokenManager;
    private final RegisterProducer registerProducer;
    private final ChangeStatusProducer changeStatusProducer;


    public AuthService(IAuthRepository repository, JwtTokenManager tokenManager, RegisterProducer registerProducer, ChangeStatusProducer changeStatusProducer) {
        super(repository);
        this.repository = repository;
        this.tokenManager = tokenManager;
        this.registerProducer = registerProducer;

        this.changeStatusProducer = changeStatusProducer;
    }

    public AuthRegisterResponseDto register(RegisterRequestDto dto) {
        if(repository.isEmail(dto.getEmail()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_EMAIL);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        /**
         * Repo -> repository.save(auth); bu bana kaydettiği entityi döner
         * Servi -> save(auth); bu da bana kaydettiği entityi döner
         * direkt -> auth, bir şekilde kayıt edilen entity nin bilgileri istenir ve bunu döner.
         */
//        return repository.save(auth);
        auth.setActivationCode(CodeGenerator.generateCode());
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
        Optional <Long> id= tokenManager.getIdFromToken(token);
        if(id.isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        if (findById(id.get()).isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        return findAll();
    }
}
