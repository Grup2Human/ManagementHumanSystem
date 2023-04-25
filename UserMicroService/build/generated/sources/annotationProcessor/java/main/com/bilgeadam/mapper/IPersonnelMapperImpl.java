package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.PersonnelSaveRequestDto;
import com.bilgeadam.dto.response.PersonnelSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Personnel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-25T16:35:29+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class IPersonnelMapperImpl implements IPersonnelMapper {

    @Override
    public Personnel toPersonnelProfile(PersonnelSaveRequestDto dto) {
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
    public PersonnelSummaryResponseDto toPersonnelProfileSummaryResponse(Personnel personnel) {
        if ( personnel == null ) {
            return null;
        }

        PersonnelSummaryResponseDto.PersonnelSummaryResponseDtoBuilder personnelSummaryResponseDto = PersonnelSummaryResponseDto.builder();

        personnelSummaryResponseDto.name( personnel.getName() );
        personnelSummaryResponseDto.surname( personnel.getSurname() );
        personnelSummaryResponseDto.email( personnel.getEmail() );
        personnelSummaryResponseDto.photo( personnel.getPhoto() );
        personnelSummaryResponseDto.address( personnel.getAddress() );

        return personnelSummaryResponseDto.build();
    }
}
