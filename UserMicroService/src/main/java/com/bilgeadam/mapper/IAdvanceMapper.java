package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateAdvanceRequestDto;
import com.bilgeadam.dto.response.LeaveDemandsResponseDto;
import com.bilgeadam.repository.entity.Advance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAdvanceMapper {
    IAdvanceMapper INSTANCE = Mappers.getMapper(IAdvanceMapper.class);
    Advance toAdvance (final CreateAdvanceRequestDto dto);
//    @Mapping(source = "id",target ="advanceId")
//    LeaveDemandsResponseDto todemandsResponseDto (final Advance advance);
}
