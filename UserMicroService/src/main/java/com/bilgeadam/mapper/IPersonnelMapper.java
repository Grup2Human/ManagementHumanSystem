package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.PersonnelSaveRequestDto;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.dto.response.PersonnelSummaryResponseDto;
import com.bilgeadam.repository.entity.Personnel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IPersonnelMapper {

    IPersonnelMapper INSTANCE = Mappers.getMapper(IPersonnelMapper.class);
    Personnel toPersonnel(final CreatePersonModel model);

    Personnel toPersonnel(final PersonnelSaveRequestDto dto);

    Personnel toPersonnel(final RegisterModel model);

    PersonnelSummaryResponseDto toPersonnelProfileSummaryResponse (final Personnel personnel);
}
