package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.PersonnelSaveRequestDto;
import com.bilgeadam.dto.response.PersonnelSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Personnel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T19:34:45+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IPersonnelMapperImpl implements IPersonnelMapper {

    @Override
    public Personnel toPersonnel(CreatePersonModel model) {
        if ( model == null ) {
            return null;
        }

        Personnel.PersonnelBuilder<?, ?> personnel = Personnel.builder();

        personnel.email( model.getEmail() );

        return personnel.build();
    }

    @Override
    public Personnel toPersonnel(PersonnelSaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Personnel.PersonnelBuilder<?, ?> personnel = Personnel.builder();

        personnel.email( dto.getEmail() );
        personnel.salary( dto.getSalary() );

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
    public PersonnelSummaryResponseDto toPersonnelProfileSummaryResponse(Personnel personnel) {
        if ( personnel == null ) {
            return null;
        }

        PersonnelSummaryResponseDto.PersonnelSummaryResponseDtoBuilder personnelSummaryResponseDto = PersonnelSummaryResponseDto.builder();

        personnelSummaryResponseDto.name( personnel.getName() );
        personnelSummaryResponseDto.surname( personnel.getSurname() );
        personnelSummaryResponseDto.email( personnel.getEmail() );
        personnelSummaryResponseDto.companyId( personnel.getCompanyId() );

        return personnelSummaryResponseDto.build();
    }
}
