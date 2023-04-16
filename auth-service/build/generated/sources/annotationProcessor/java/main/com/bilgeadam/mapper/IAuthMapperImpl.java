package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-12T08:29:09+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth toAuth(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.username( dto.getUsername() );
        auth.email( dto.getEmail() );
        auth.password( dto.getPassword() );

        return auth.build();
    }

    @Override
    public RegisterResponseDto toRegisterResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterResponseDto.RegisterResponseDtoBuilder registerResponseDto = RegisterResponseDto.builder();

        registerResponseDto.id( auth.getId() );
        registerResponseDto.activationCode( auth.getActivationCode() );
        registerResponseDto.username( auth.getUsername() );

        return registerResponseDto.build();
    }

    @Override
    public NewCreateUserRequestDto toNewCreateUserRequestDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        NewCreateUserRequestDto.NewCreateUserRequestDtoBuilder newCreateUserRequestDto = NewCreateUserRequestDto.builder();

        newCreateUserRequestDto.authId( auth.getId() );
        newCreateUserRequestDto.username( auth.getUsername() );
        newCreateUserRequestDto.email( auth.getEmail() );

        return newCreateUserRequestDto.build();
    }

    @Override
    public RegisterModel toRegisterModel(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterModel.RegisterModelBuilder registerModel = RegisterModel.builder();

        registerModel.authId( auth.getId() );
        registerModel.username( auth.getUsername() );
        registerModel.email( auth.getEmail() );

        return registerModel.build();
    }
}
