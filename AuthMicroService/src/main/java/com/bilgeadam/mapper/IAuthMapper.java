package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.response.AuthRegisterResponseDto;
import com.bilgeadam.rabbitmq.model.ChangeStatusModel;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth toAuth(final RegisterRequestDto dto);
    Auth toAuth(final CreatePersonModel model);

    @Mapping(target = "authid", source = "id") //Target:UserProfileSaveRequest //Source: Auth
    UserProfileSaveRequestDto fromAuth(final Auth auth);
    AuthRegisterResponseDto toAuthResponseDto (final Auth auth);
    @Mapping(source = "id",target = "authId")
    RegisterModel toRegisterModel(final Auth auth);
    @Mapping(source = "id",target = "authId")
    ChangeStatusModel toActivationModel(final Auth auth);

}
