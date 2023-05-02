package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AddPersonnelRequestDto;
import com.bilgeadam.repository.entity.Personnel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T14:35:49+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IPersonnelMapperImpl implements IPersonnelMapper {

    @Override
    public Personnel toPersonnel(AddPersonnelRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Personnel.PersonnelBuilder<?, ?> personnel = Personnel.builder();

        personnel.personnelId( dto.getId() );
        if ( dto.getId() != null ) {
            personnel.id( String.valueOf( dto.getId() ) );
        }
        personnel.authId( dto.getAuthId() );
        personnel.startDate( dto.getStartDate() );
        personnel.terminationDate( dto.getTerminationDate() );
        personnel.profession( dto.getProfession() );
        personnel.department( dto.getDepartment() );
        personnel.companyId( dto.getCompanyId() );
        personnel.status( dto.getStatus() );

        return personnel.build();
    }
}
