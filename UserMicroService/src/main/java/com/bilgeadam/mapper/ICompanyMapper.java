package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CompanySaveRequestDto;
import com.bilgeadam.dto.request.UpdateCompanyRequestDto;
import com.bilgeadam.dto.response.CompanySummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICompanyMapper {
    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);
    Company toCompany(final CompanySaveRequestDto dto);
    Company toCompany(final RegisterModel model);
    Company toCompany(final UpdateCompanyRequestDto dto);
    CompanySummaryResponseDto toCompanyProfileSummaryResponse (final Company company);



}
