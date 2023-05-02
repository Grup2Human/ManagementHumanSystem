package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateLeaveRequestDto;
import com.bilgeadam.dto.response.DemandsResponseDto;
import com.bilgeadam.repository.entity.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ILeaveMapper {
    ILeaveMapper INSTANCE = Mappers.getMapper(ILeaveMapper.class);
    Leave toLeave (final CreateLeaveRequestDto dto);
    DemandsResponseDto todemandsResponseDto (final Leave leave);
}
