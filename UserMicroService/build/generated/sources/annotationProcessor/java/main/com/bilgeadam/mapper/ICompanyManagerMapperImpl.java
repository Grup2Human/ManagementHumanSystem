package com.bilgeadam.mapper;

import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.CompanyManager;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T15:24:17+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class ICompanyManagerMapperImpl implements ICompanyManagerMapper {

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
}
