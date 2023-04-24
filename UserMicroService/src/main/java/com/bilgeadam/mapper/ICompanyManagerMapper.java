package com.bilgeadam.mapper;

import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.CompanyManager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICompanyManagerMapper {

    ICompanyManagerMapper INSTANCE = Mappers.getMapper(ICompanyManagerMapper.class);

    CompanyManager toCompanyManager(final RegisterModel model);
}
