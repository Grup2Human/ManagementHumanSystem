package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AddAdminRequestDto;
import com.bilgeadam.repository.entity.Admin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-27T01:34:40+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAdminMapperImpl implements IAdminMapper {

    @Override
    public Admin toAdmin(AddAdminRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Admin.AdminBuilder<?, ?> admin = Admin.builder();

        admin.adminId( dto.getId() );
        if ( dto.getId() != null ) {
            admin.id( String.valueOf( dto.getId() ) );
        }
        admin.authId( dto.getAuthId() );

        return admin.build();
    }
}
