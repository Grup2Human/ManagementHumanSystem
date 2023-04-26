package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AddCompanyRequestDto;
import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.repository.entity.CompanyManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICompanyMapper {
    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);
    @Mapping(target = "companyId",source = "id")
    Company toCompany(final AddCompanyRequestDto dto);



}
