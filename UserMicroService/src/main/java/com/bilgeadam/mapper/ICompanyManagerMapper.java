package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AdminSaveRequestDto;
import com.bilgeadam.dto.request.CompanyManagerSaveRequestDto;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.repository.entity.CompanyManager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICompanyManagerMapper {

    ICompanyManagerMapper INSTANCE = Mappers.getMapper(ICompanyManagerMapper.class);

    CompanyManager toCompanyManagerProfile(final CompanyManagerSaveRequestDto dto);
    CompanyManager toCompanyManager(final RegisterModel model);

    AdminSummaryResponseDto toCompanyManagerProfileSummaryResponse (final CompanyManager companyManager);
}
