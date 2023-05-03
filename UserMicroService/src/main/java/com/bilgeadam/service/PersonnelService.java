package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateLeaveRequestDto;
import com.bilgeadam.dto.request.UpdatePersonnelRequestDto;
import com.bilgeadam.dto.response.DemandsResponseDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.ILeaveMapper;
import com.bilgeadam.rabbitmq.model.AddAuthIdModel;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Leave;
import com.bilgeadam.repository.entity.Personnel;
import com.bilgeadam.repository.enums.ELeaveApprovalStatus;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class PersonnelService extends ServiceManager<Personnel,Long> {

    private final IPersonnelRepository iPersonnelRepository;
    private final JwtTokenManager tokenManager;
    private final LeaveService leaveService;

    public PersonnelService(IPersonnelRepository iPersonnelRepository, JwtTokenManager tokenManager, LeaveService leaveService) {
        super(iPersonnelRepository);
        this.iPersonnelRepository = iPersonnelRepository;
        this.tokenManager = tokenManager;
        this.leaveService = leaveService;
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
    public Boolean createLeaveRequest(CreateLeaveRequestDto dto,String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<Personnel> personnel = iPersonnelRepository.findOptionalByAuthId(authId.get());
        if (personnel.isEmpty()) {
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }

        Leave leave = ILeaveMapper.INSTANCE.toLeave(dto);
        leave.setPersonnelId(personnel.get().getId());
        leave.setDuration(ChronoUnit.DAYS.between(dto.getStartDate(),dto.getEndDate()));
        leaveService.save(leave);
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
    public Boolean createAuthId(AddAuthIdModel model) {
        System.err.println(model.getAuthId());
        Optional<Personnel> personnel = iPersonnelRepository.findOptionalByEmail(model.getEmail());
        if(!personnel.isPresent())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        personnel.get().setAuthId(model.getAuthId());
        update(personnel.get());
        return true;
    }
//    public List<DemandsResponseDto> findAllLeaveRequests (String token) {
//        Optional<Long> authId = tokenManager.getIdFromToken(token);
//        System.out.println(authId.get());
//        if (authId.isEmpty())
//            throw new UserManagerException(EErrorType.INVALID_TOKEN);
//        List<Leave> leavelist = leaveService.findAll();
//        if (leavelist.size()==0)
//            throw new UserManagerException(EErrorType.LEAVE_NOT_FOUND);
//        //---------------Buraya filtreleme eklemeliyiz
//        List<DemandsResponseDto> demandsResponseDtoList = new ArrayList<>();
//        Optional<Personnel> personnel= iPersonnelRepository.findOptionalByAuthId(authId.get());
//
//        if(personnel.isEmpty())
//            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
//
//        leavelist.stream()
//                .filter(a-> a.getPersonnelId()==personnel.get().getId())
//                .forEach(x -> {
//                    demandsResponseDtoList.add(ILeaveMapper.INSTANCE.todemandsResponseDto(x));
//                });
//        return demandsResponseDtoList;
//    }

    public List<Leave> findAllLeaveRequests2 (String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        System.out.println(authId.get());
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        List<Leave> leavelist = leaveService.findAll();
        if (leavelist.size()==0)
            throw new UserManagerException(EErrorType.LEAVE_NOT_FOUND);
        //---------------Buraya filtreleme eklemeliyiz
        List<Leave> leaveList2 = new ArrayList<>();
        Optional<Personnel> personnel= iPersonnelRepository.findOptionalByAuthId(authId.get());

        if(personnel.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);

        leavelist.stream()
                .filter(a-> a.getPersonnelId()==personnel.get().getId())
                .sorted(Comparator.comparing(
                        (Leave a) -> a.getELeaveApprovalStatus() == ELeaveApprovalStatus.PENDINGAPPROVAL ? 0 : 1).reversed()
                        // sort by status, with PENDING first
                .thenComparingLong(Leave::getCreateat).reversed())// then sort by descending createat
                //.sorted(Comparator.comparingLong(Leave::getCreateat).reversed())
                .forEach(x -> {
                    leaveList2.add(x);
                });
        return leaveList2;
    }
}
