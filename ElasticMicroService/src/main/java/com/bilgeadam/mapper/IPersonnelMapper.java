package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.AddPersonnelRequestDto;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Personnel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IPersonnelMapper {

    IPersonnelMapper INSTANCE = Mappers.getMapper(IPersonnelMapper.class);
    @Mapping(target = "personnelId",source = "id")
    Personnel toPersonnel(final AddPersonnelRequestDto dto);
}
