package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.response.AuthRegisterResponseDto;
import com.bilgeadam.rabbitmq.model.ChangeStatusModel;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T19:30:37+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth toAuth(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.email( dto.getEmail() );
        auth.password( dto.getPassword() );

        return auth.build();
    }

    @Override
    public Auth toAuth(CreatePersonModel model) {
        if ( model == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.email( model.getEmail() );
        auth.password( model.getPassword() );
        auth.role( model.getRole() );

        return auth.build();
    }

    @Override
    public UserProfileSaveRequestDto fromAuth(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        UserProfileSaveRequestDto.UserProfileSaveRequestDtoBuilder userProfileSaveRequestDto = UserProfileSaveRequestDto.builder();

        userProfileSaveRequestDto.authid( auth.getId() );
        userProfileSaveRequestDto.email( auth.getEmail() );

        return userProfileSaveRequestDto.build();
    }

    @Override
    public AuthRegisterResponseDto toAuthResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        AuthRegisterResponseDto.AuthRegisterResponseDtoBuilder authRegisterResponseDto = AuthRegisterResponseDto.builder();

        authRegisterResponseDto.id( auth.getId() );
        authRegisterResponseDto.email( auth.getEmail() );
        authRegisterResponseDto.activationCode( auth.getActivationCode() );

        return authRegisterResponseDto.build();
    }

    @Override
    public RegisterModel toRegisterModel(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterModel.RegisterModelBuilder registerModel = RegisterModel.builder();

        registerModel.authId( auth.getId() );
        registerModel.email( auth.getEmail() );

        return registerModel.build();
    }

    @Override
    public ChangeStatusModel toActivationModel(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        ChangeStatusModel.ChangeStatusModelBuilder changeStatusModel = ChangeStatusModel.builder();

        changeStatusModel.authId( auth.getId() );
        changeStatusModel.activationCode( auth.getActivationCode() );
        changeStatusModel.status( auth.getStatus() );

        return changeStatusModel.build();
    }
}
