package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AdminSaveRequestDto;
import com.bilgeadam.dto.request.UpdateAdminRequestDto;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAdminMapper {
    IAdminMapper INSTANCE = Mappers.getMapper(IAdminMapper.class);
    Admin toAdmin(final AdminSaveRequestDto dto);
    Admin toAdminProfile(final RegisterModel model);
    Admin toAdminProfile(final UpdateAdminRequestDto dto);
    AdminSummaryResponseDto toAdminProfileSummaryResponse (final Admin admin);



}
