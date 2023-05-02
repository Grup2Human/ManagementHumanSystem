package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AddCompanyRequestDto;
import com.bilgeadam.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T14:35:49+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ICompanyMapperImpl implements ICompanyMapper {

    @Override
    public Company toCompany(AddCompanyRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

        company.companyId( dto.getId() );
        if ( dto.getId() != null ) {
            company.id( String.valueOf( dto.getId() ) );
        }
        company.companyName( dto.getCompanyName() );
        company.companyType( dto.getCompanyType() );
        company.centralRegistrationSystemNo( dto.getCentralRegistrationSystemNo() );
        company.taxId( dto.getTaxId() );
        company.taxOffice( dto.getTaxOffice() );
        company.logo( dto.getLogo() );
        company.phone( dto.getPhone() );
        company.address( dto.getAddress() );
        company.email( dto.getEmail() );
        company.headcount( dto.getHeadcount() );
        company.foundationYear( dto.getFoundationYear() );
        company.contractStartDate( dto.getContractStartDate() );
        company.contractEndDate( dto.getContractEndDate() );

        return company.build();
    }
}
