package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AddCompanyManagerRequestDto;
import com.bilgeadam.repository.entity.CompanyManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICompanyManagerMapper {

    ICompanyManagerMapper INSTANCE = Mappers.getMapper(ICompanyManagerMapper.class);
    @Mapping(target = "companyManagerId",source = "id")
    CompanyManager toCompanyManager(final AddCompanyManagerRequestDto dto);
}
