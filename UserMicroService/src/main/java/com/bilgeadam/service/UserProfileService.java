package com.bilgeadam.service;

import com.bilgeadam.dto.request.UpdateUserRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.response.UserProfileSummaryResponseDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.IUserProfileMapper;
import com.bilgeadam.rabbitmq.model.ChangeStatusModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.IUserProfileRepository;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;
    private final JwtTokenManager tokenManager;


    public UserProfileService(IUserProfileRepository repository, JwtTokenManager tokenManager) {
        super(repository);
        this.repository = repository;
        this.tokenManager = tokenManager;
    }

    public Boolean saveDto(UserProfileSaveRequestDto dto) {
        save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
        return true;
    }

    public Boolean createUser(RegisterModel model) {
        try {
            UserProfile userProfile = save(IUserProfileMapper.INSTANCE.toUserProfile(model));
            save(userProfile);
            return true;
        } catch (Exception e) {
            throw new UserManagerException(EErrorType.USER_NOT_CREATED);
        }
    }
   public void changeUserStatus(ChangeStatusModel model) {
       Optional<UserProfile> userProfile = repository.findOptionalByAuthId(model.getAuthId());
       if (userProfile.isEmpty())
           throw new UserManagerException(EErrorType.USER_NOT_FOUND);
       userProfile.get().setStatus(model.getStatus());
       update(userProfile.get());
   }
    public Boolean updateUser(UpdateUserRequestDto dto) {
            Optional<Long> authId = tokenManager.getIdFromToken(dto.getToken());
            if (authId.isEmpty()) {
                throw new UserManagerException(EErrorType.INVALID_TOKEN);
            }
            Optional<UserProfile> userProfile = repository.findOptionalByAuthId(authId.get());
            if (userProfile.isEmpty()) {
                throw new UserManagerException(EErrorType.USER_NOT_FOUND);
            }
            userProfile.get().setName(dto.getName());
            userProfile.get().setNameSecond(dto.getNameSecond());
            userProfile.get().setSurname(dto.getSurname());
            userProfile.get().setSurnameSecond(dto.getSurnameSecond());
            userProfile.get().setPhoneNumber(dto.getPhoneNumber());
            userProfile.get().setPhoto(dto.getPhoto());
            userProfile.get().setAddress(dto.getAddress());
            userProfile.get().setBirthday(dto.getBirthday());
            userProfile.get().setBirthPlace(dto.getBirthPlace());
            userProfile.get().setTC(dto.getTC());
            update(userProfile.get());
            return true;
        }

    public UserProfile findByIdWithToken(String token, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<UserProfile> userProfile = repository.findOptionalByAuthId(id);
        if (userProfile.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        return userProfile.get();
    }

    public List<UserProfileSummaryResponseDto> findAllSummary() {
        List<UserProfile> userProfiles = findAll();
        List<UserProfileSummaryResponseDto> userProfileSummaryResponseDtoList = new ArrayList<>();
        userProfiles.forEach(x-> {
            userProfileSummaryResponseDtoList.add(IUserProfileMapper.INSTANCE.toUserProfileSummaryResponse(x));
        });
        return userProfileSummaryResponseDtoList;
    }

}
