package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AddAdminRequestDto;
import com.bilgeadam.repository.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAdminMapper {
    IAdminMapper INSTANCE = Mappers.getMapper(IAdminMapper.class);
    @Mapping(target = "adminId",source = "id")
    Admin toAdmin(final AddAdminRequestDto dto);
}
