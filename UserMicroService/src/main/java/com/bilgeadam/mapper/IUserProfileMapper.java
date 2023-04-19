package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.UpdateUserRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.response.UserProfileSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);
    UserProfile toUserProfile(final UserProfileSaveRequestDto dto);
    UserProfile toUserProfile(final RegisterModel model);
    UserProfile toUserProfile(final UpdateUserRequestDto dto);
    UserProfileSummaryResponseDto toUserProfileSummaryResponse (final UserProfile userProfile);



}
