package com.bilgeadam.service;

import com.bilgeadam.dto.request.UpdateCompanyManagerRequestDto;
import com.bilgeadam.dto.request.UpdatePersonnelRequestDto;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.ICompanyManagerMapper;
import com.bilgeadam.repository.ICompanyManagerRepository;
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
public class CompanyManagerService extends ServiceManager<CompanyManager,Long> {

    private final ICompanyManagerRepository companyManagerRepository;
    private final JwtTokenManager tokenManager;
    private final IPersonnelRepository personnelRepository;
    private final PersonnelService personnelService;


    public CompanyManagerService(ICompanyManagerRepository companyManagerRepository, JwtTokenManager tokenManager, IPersonnelRepository personnelRepository, PersonnelService personnelService) {
        super(companyManagerRepository);
        this.companyManagerRepository = companyManagerRepository;
        this.tokenManager = tokenManager;
        this.personnelRepository = personnelRepository;
        this.personnelService = personnelService;
    }

//    public Boolean createPersonnel(RegisterModel model) {
//        try {
//            Personnel personnel = save(IPersonnelMapper.INSTANCE.toPersonnel(model));
//            save(personnel);
//            return true;
//        } catch (Exception e) {
//            throw new UserManagerException(EErrorType.USER_NOT_CREATED);
//        }
//    }

    public Boolean updateCompanyManager(UpdateCompanyManagerRequestDto dto) {
        Optional<Long> authId = tokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<CompanyManager> companyManagerProfile = companyManagerRepository.findOptionalByAuthId(authId.get());
        if (companyManagerProfile.isEmpty()) {
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }
        companyManagerProfile.get().setName(dto.getName());
        companyManagerProfile.get().setNameSecond(dto.getNameSecond());
        companyManagerProfile.get().setSurname(dto.getSurname());
        companyManagerProfile.get().setSurnameSecond(dto.getSurnameSecond());
        companyManagerProfile.get().setPhoneNumber(dto.getPhoneNumber());
        companyManagerProfile.get().setPhoto(dto.getPhoto());
        companyManagerProfile.get().setAddress(dto.getAddress());
        companyManagerProfile.get().setBirthday(dto.getBirthday());
        companyManagerProfile.get().setBirthPlace(dto.getBirthPlace());
        companyManagerProfile.get().setTC(dto.getTC());
        companyManagerProfile.get().setStartDate(dto.getStartDate());
        companyManagerProfile.get().setProfession(dto.getProfession());
        companyManagerProfile.get().setDepartment(dto.getDepartment());
        companyManagerProfile.get().setCompanyId(dto.getCompanyId());
        update(companyManagerProfile.get());
        return true;
    }

    public Boolean updatePersonnel(UpdatePersonnelRequestDto dto) {
        Optional<Long> authId = tokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<Personnel> personnelProfile = personnelRepository.findOptionalByAuthId(authId.get());
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
        personnelService.update(personnelProfile.get());
        return true;
    }

    public CompanyManager findByIdWithToken(String token, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<CompanyManager> companyManager = companyManagerRepository.findOptionalByAuthId(id);
        if (companyManager.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        return companyManager.get();
    }

//    public List<CompanyManagerSummaryResponseDto> findAllSummary() {
//        List<CompanyManager> companyManager = findAll();
//        List<CompanyManagerSummaryResponseDto> CompanyManagerSummaryResponseDtoList = new ArrayList<>();
//        companyManager.forEach(x-> {
//            CompanyManagerSummaryResponseDtoList
//                    .add(ICompanyManagerMapper.INSTANCE.toCompanyManagerProfileSummaryResponse(x));
//        });
//        return CompanyManagerSummaryResponseDtoList;
//    }



}
