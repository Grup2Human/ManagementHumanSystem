package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth toAuth(final RegisterRequestDto dto);
    @Mapping(target = "authid", source = "id") //Target:UserProfileSaveRequest //Source: Auth
    UserProfileSaveRequestDto fromAuth(final Auth auth);

}
