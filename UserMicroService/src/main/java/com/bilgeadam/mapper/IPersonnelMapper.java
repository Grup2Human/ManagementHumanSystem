package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CompanyManagerSaveRequestDto;
import com.bilgeadam.dto.request.PersonnelSaveRequestDto;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.dto.response.PersonnelSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Personnel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IPersonnelMapper {

    IPersonnelMapper INSTANCE = Mappers.getMapper(IPersonnelMapper.class);

    Personnel toPersonnelProfile(final PersonnelSaveRequestDto dto);
    Personnel toPersonnel(final RegisterModel model);

    PersonnelSummaryResponseDto toPersonnelProfileSummaryResponse (final Personnel personnel);
}
