package com.bilgeadam.service;

import com.bilgeadam.dto.request.UpdateUserRequestDto;
import com.bilgeadam.dto.request.AdminSaveRequestDto;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.IAdminMapper;
import com.bilgeadam.rabbitmq.model.ChangeStatusModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.IAdminRepository;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService extends ServiceManager<Admin,Long> {
    private final IAdminRepository repository;
    private final JwtTokenManager tokenManager;


    public AdminService(IAdminRepository repository, JwtTokenManager tokenManager) {
        super(repository);
        this.repository = repository;
        this.tokenManager = tokenManager;
    }

    public Boolean saveDto(AdminSaveRequestDto dto) {
        save(IAdminMapper.INSTANCE.toUserProfile(dto));
        return true;
    }

    public Boolean createUser(RegisterModel model) {
        try {
            Admin admin = save(IAdminMapper.INSTANCE.toUserProfile(model));
            save(admin);
            return true;
        } catch (Exception e) {
            throw new UserManagerException(EErrorType.USER_NOT_CREATED);
        }
    }
   public void changeUserStatus(ChangeStatusModel model) {
       Optional<Admin> userProfile = repository.findOptionalByAuthId(model.getAuthId());
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
            Optional<Admin> userProfile = repository.findOptionalByAuthId(authId.get());
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

    public Admin findByIdWithToken(String token, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<Admin> userProfile = repository.findOptionalByAuthId(id);
        if (userProfile.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        return userProfile.get();
    }

    public List<AdminSummaryResponseDto> findAllSummary() {
        List<Admin> admins = findAll();
        List<AdminSummaryResponseDto> adminSummaryResponseDtoList = new ArrayList<>();
        admins.forEach(x-> {
            adminSummaryResponseDtoList.add(IAdminMapper.INSTANCE.toUserProfileSummaryResponse(x));
        });
        return adminSummaryResponseDtoList;
    }

}
