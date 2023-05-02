package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AddCompanyManagerRequestDto;
import com.bilgeadam.repository.entity.CompanyManager;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T14:35:49+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ICompanyManagerMapperImpl implements ICompanyManagerMapper {

    @Override
    public CompanyManager toCompanyManager(AddCompanyManagerRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        CompanyManager.CompanyManagerBuilder<?, ?> companyManager = CompanyManager.builder();

        companyManager.companyManagerId( dto.getId() );
        if ( dto.getId() != null ) {
            companyManager.id( String.valueOf( dto.getId() ) );
        }
        companyManager.authId( dto.getAuthId() );
        companyManager.startDate( dto.getStartDate() );
        companyManager.profession( dto.getProfession() );
        companyManager.department( dto.getDepartment() );
        companyManager.companyId( dto.getCompanyId() );
        companyManager.status( dto.getStatus() );

        return companyManager.build();
    }
}
