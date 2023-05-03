package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CompanyManagerSaveRequestDto;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.CompanyManager;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T19:24:49+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ICompanyManagerMapperImpl implements ICompanyManagerMapper {

    @Override
    public CompanyManager toCompanyManager(CreatePersonModel model) {
        if ( model == null ) {
            return null;
        }

        CompanyManager.CompanyManagerBuilder<?, ?> companyManager = CompanyManager.builder();

        companyManager.email( model.getEmail() );

        return companyManager.build();
    }

    @Override
    public CompanyManager toCompanyManagerProfile(CompanyManagerSaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        CompanyManager.CompanyManagerBuilder<?, ?> companyManager = CompanyManager.builder();

        companyManager.email( dto.getEmail() );

        return companyManager.build();
    }

    @Override
    public CompanyManager toCompanyManager(RegisterModel model) {
        if ( model == null ) {
            return null;
        }

        CompanyManager.CompanyManagerBuilder<?, ?> companyManager = CompanyManager.builder();

        companyManager.email( model.getEmail() );
        companyManager.authId( model.getAuthId() );
        companyManager.status( model.getStatus() );

        return companyManager.build();
    }

    @Override
    public CompanyManagerSummaryResponseDto toCompanyManagerProfileSummaryResponse(CompanyManager companyManager) {
        if ( companyManager == null ) {
            return null;
        }

        CompanyManagerSummaryResponseDto.CompanyManagerSummaryResponseDtoBuilder companyManagerSummaryResponseDto = CompanyManagerSummaryResponseDto.builder();

        companyManagerSummaryResponseDto.name( companyManager.getName() );
        companyManagerSummaryResponseDto.surname( companyManager.getSurname() );
        companyManagerSummaryResponseDto.email( companyManager.getEmail() );
        companyManagerSummaryResponseDto.photo( companyManager.getPhoto() );
        companyManagerSummaryResponseDto.companyId( companyManager.getCompanyId() );

        return companyManagerSummaryResponseDto.build();
    }
}
