package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CompanySaveRequestDto;
import com.bilgeadam.dto.request.UpdateCompanyRequestDto;
import com.bilgeadam.dto.response.CompanySummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T19:24:49+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ICompanyMapperImpl implements ICompanyMapper {

    @Override
    public Company toCompany(CompanySaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

        company.email( dto.getEmail() );

        return company.build();
    }

    @Override
    public Company toCompany(RegisterModel model) {
        if ( model == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

        company.email( model.getEmail() );

        return company.build();
    }

    @Override
    public Company toCompany(UpdateCompanyRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

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

    @Override
    public CompanySummaryResponseDto toCompanyProfileSummaryResponse(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanySummaryResponseDto.CompanySummaryResponseDtoBuilder companySummaryResponseDto = CompanySummaryResponseDto.builder();

        companySummaryResponseDto.companyType( company.getCompanyType() );
        companySummaryResponseDto.email( company.getEmail() );
        companySummaryResponseDto.phone( company.getPhone() );
        companySummaryResponseDto.address( company.getAddress() );

        return companySummaryResponseDto.build();
    }
}
