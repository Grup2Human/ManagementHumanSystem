package com.bilgeadam.service;

import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.exception.AuthServiceException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.repository.IAuthRepository;
import com.bilgeadam.repository.entity.Auth;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;

    public AuthService(IAuthRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Auth register(RegisterRequestDto dto) {
        if(repository.isEmail(dto.getEmail()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_EMAIL);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        /**
         * Repo -> repository.save(auth); bu bana kaydettiği entityi döner
         * Servi -> save(auth); bu da bana kaydettiği entityi döner
         * direkt -> auth, bir şekilde kayıt edilen entity nin bilgileri istenir ve bunu döner.
         */
//        return repository.save(auth);
        save(auth);
//        iUserProfileManager.save(IAuthMapper.INSTANCE.fromAuth(auth));
//        createUserProducer.convertAndSend(SaveAuthModel.builder()
//                        .authid(auth.getId())
//                        .email(auth.getEmail())
//                        .email(auth.getEmail())
//                .build());
        return auth;

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
        return "Parola: Cukibik. Fikibok da olabilir";
    }
    public List<Auth> findAll (String parola) {
        if(!(parola.equals("Cukibik") || parola.equals("Fikibok")))
            throw new AuthServiceException(EErrorType.METHOD_MIS_MATCH_ERROR);
        return findAll();
    }
}
