package com.bilgeadam.service;

import com.bilgeadam.dto.request.UpdateCompanyManagerRequestDto;
import com.bilgeadam.dto.request.UpdatePersonnelRequestDto;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.dto.response.PersonnelSummaryResponseDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.ICompanyManagerMapper;
import com.bilgeadam.mapper.IPersonnelMapper;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Personnel;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService extends ServiceManager<Personnel,Long> {

    private final IPersonnelRepository iPersonnelRepository;
    private final JwtTokenManager tokenManager;

    public PersonnelService(IPersonnelRepository iPersonnelRepository, JwtTokenManager tokenManager) {
        super(iPersonnelRepository);
        this.iPersonnelRepository = iPersonnelRepository;
        this.tokenManager = tokenManager;
    }

    public Boolean updatePersonnel(UpdatePersonnelRequestDto dto) {
        Optional<Long> authId = tokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<Personnel> personnelProfile = iPersonnelRepository.findOptionalByAuthId(authId.get());
        if (personnelProfile.isEmpty()) {
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }
        personnelProfile.get().setName(dto.getName());
        personnelProfile.get().setNameSecond(dto.getNameSecond());
        personnelProfile.get().setSurname(dto.getSurname());
        personnelProfile.get().setSurnameSecond(dto.getSurnameSecond());
        personnelProfile.get().setPhoneNumber(dto.getPhoneNumber());
        personnelProfile.get().setPhoto(dto.getPhoto());
        personnelProfile.get().setAddress(dto.getAddress());
        personnelProfile.get().setBirthday(dto.getBirthday());
        personnelProfile.get().setBirthPlace(dto.getBirthPlace());
        personnelProfile.get().setTC(dto.getTC());
        personnelProfile.get().setStartDate(dto.getStartDate());
        personnelProfile.get().setTerminationDate(dto.getTerminationDate());
        personnelProfile.get().setProfession(dto.getProfession());
        personnelProfile.get().setDepartment(dto.getDepartment());
        personnelProfile.get().setCompanyId(dto.getCompanyId());
        update(personnelProfile.get());
        return true;
    }

//    public Personnel findByIdWithToken(String token, Long id) {
//        Optional<Long> authId = tokenManager.getIdFromToken(token);
//        if (authId.isEmpty())
//            throw new UserManagerException(EErrorType.INVALID_TOKEN);
//        Optional<Personnel> personnel = iPersonnelRepository.findOptionalByAuthId(id);
//        if (personnel.isEmpty())
//            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
//        return personnel.get();
//    }
//
//    public List<PersonnelSummaryResponseDto> findAllSummary() {
//        List<Personnel> personnelList = findAll();
//        List<PersonnelSummaryResponseDto> PersonnelSummaryResponseDtoList = new ArrayList<>();
//        personnelList.forEach(x-> {
//            PersonnelSummaryResponseDtoList.add(IPersonnelMapper.INSTANCE.toPersonnelProfileSummaryResponse(x));
//        });
//        return PersonnelSummaryResponseDtoList;
//    }
}
