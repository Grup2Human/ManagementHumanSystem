package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AdminSaveRequestDto;
import com.bilgeadam.dto.request.UpdateAdminRequestDto;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Admin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T19:24:49+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAdminMapperImpl implements IAdminMapper {

    @Override
    public Admin toAdmin(AdminSaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Admin.AdminBuilder<?, ?> admin = Admin.builder();

        admin.email( dto.getEmail() );

        return admin.build();
    }

    @Override
    public Admin toAdminProfile(RegisterModel model) {
        if ( model == null ) {
            return null;
        }

        Admin.AdminBuilder<?, ?> admin = Admin.builder();

        admin.email( model.getEmail() );
        admin.authId( model.getAuthId() );
        admin.status( model.getStatus() );

        return admin.build();
    }

    @Override
    public Admin toAdminProfile(UpdateAdminRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Admin.AdminBuilder<?, ?> admin = Admin.builder();

        admin.name( dto.getName() );
        admin.nameSecond( dto.getNameSecond() );
        admin.surname( dto.getSurname() );
        admin.surnameSecond( dto.getSurnameSecond() );
        admin.photo( dto.getPhoto() );
        admin.birthday( dto.getBirthday() );
        admin.birthPlace( dto.getBirthPlace() );
        admin.TC( dto.getTC() );
        admin.address( dto.getAddress() );
        admin.phoneNumber( dto.getPhoneNumber() );

        return admin.build();
    }

    @Override
    public AdminSummaryResponseDto toAdminProfileSummaryResponse(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        AdminSummaryResponseDto.AdminSummaryResponseDtoBuilder adminSummaryResponseDto = AdminSummaryResponseDto.builder();

        adminSummaryResponseDto.name( admin.getName() );
        adminSummaryResponseDto.surname( admin.getSurname() );
        adminSummaryResponseDto.email( admin.getEmail() );
        adminSummaryResponseDto.photo( admin.getPhoto() );
        adminSummaryResponseDto.address( admin.getAddress() );

        return adminSummaryResponseDto.build();
    }
}
