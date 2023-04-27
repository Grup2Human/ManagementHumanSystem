package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CompanyManagerSaveRequestDto;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.repository.entity.CompanyManager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICompanyManagerMapper {

    ICompanyManagerMapper INSTANCE = Mappers.getMapper(ICompanyManagerMapper.class);
    CompanyManager toCompanyManager(final CreatePersonModel model);

    CompanyManager toCompanyManagerProfile(final CompanyManagerSaveRequestDto dto);
    CompanyManager toCompanyManager(final RegisterModel model);

    CompanyManagerSummaryResponseDto toCompanyManagerProfileSummaryResponse (final CompanyManager companyManager);
}
