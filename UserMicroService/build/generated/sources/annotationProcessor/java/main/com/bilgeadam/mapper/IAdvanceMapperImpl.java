package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateAdvanceRequestDto;
import com.bilgeadam.repository.entity.Advance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T19:30:44+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAdvanceMapperImpl implements IAdvanceMapper {

    @Override
    public Advance toAdvance(CreateAdvanceRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Advance.AdvanceBuilder<?, ?> advance = Advance.builder();

        advance.companyManagerId( dto.getCompanyManagerId() );
        advance.demandDate( dto.getDemandDate() );
        advance.advanceAmount( dto.getAdvanceAmount() );
        advance.currency( dto.getCurrency() );
        advance.demandType( dto.getDemandType() );
        advance.description( dto.getDescription() );

        return advance.build();
    }
}
