package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.PersonnelSaveRequestDto;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Personnel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-27T01:23:37+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IPersonnelMapperImpl implements IPersonnelMapper {

    @Override
    public Personnel toPersonnel(PersonnelSaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Personnel.PersonnelBuilder<?, ?> personnel = Personnel.builder();

        personnel.email( dto.getEmail() );

        return personnel.build();
    }

    @Override
    public Personnel toPersonnel(RegisterModel model) {
        if ( model == null ) {
            return null;
        }

        Personnel.PersonnelBuilder<?, ?> personnel = Personnel.builder();

        personnel.email( model.getEmail() );
        personnel.authId( model.getAuthId() );
        personnel.status( model.getStatus() );

        return personnel.build();
    }

    @Override
    public AdminSummaryResponseDto toPersonnelProfileSummaryResponse(Personnel personnel) {
        if ( personnel == null ) {
            return null;
        }

        AdminSummaryResponseDto.AdminSummaryResponseDtoBuilder adminSummaryResponseDto = AdminSummaryResponseDto.builder();

        adminSummaryResponseDto.name( personnel.getName() );
        adminSummaryResponseDto.surname( personnel.getSurname() );
        adminSummaryResponseDto.email( personnel.getEmail() );
        adminSummaryResponseDto.photo( personnel.getPhoto() );
        adminSummaryResponseDto.address( personnel.getAddress() );

        return adminSummaryResponseDto.build();
    }
}
