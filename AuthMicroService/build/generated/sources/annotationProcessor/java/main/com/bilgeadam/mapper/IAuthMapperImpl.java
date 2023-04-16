package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-17T00:52:51+0300",
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

        auth.name( dto.getName() );
        auth.nameSecond( dto.getNameSecond() );
        auth.surname( dto.getSurname() );
        auth.surnameSecond( dto.getSurnameSecond() );
        auth.email( dto.getEmail() );
        auth.password( dto.getPassword() );
        auth.status( dto.getStatus() );

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
}
