package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.UpdateUserRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-18T11:32:32+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class IUserProfileMapperImpl implements IUserProfileMapper {

    @Override
    public UserProfile toUserProfile(UserProfileSaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.email( dto.getEmail() );

        return userProfile.build();
    }

    @Override
    public UserProfile toUserProfile(RegisterModel model) {
        if ( model == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.authId( model.getAuthId() );
        userProfile.email( model.getEmail() );

        return userProfile.build();
    }

    @Override
    public UserProfile toUserProfile(UpdateUserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.name( dto.getName() );
        userProfile.nameSecond( dto.getNameSecond() );
        userProfile.surname( dto.getSurname() );
        userProfile.surnameSecond( dto.getSurnameSecond() );
        userProfile.photo( dto.getPhoto() );
        userProfile.birthday( dto.getBirthday() );
        userProfile.birthPlace( dto.getBirthPlace() );
        userProfile.TC( dto.getTC() );
        userProfile.address( dto.getAddress() );
        userProfile.phoneNumber( dto.getPhoneNumber() );

        return userProfile.build();
    }
}
