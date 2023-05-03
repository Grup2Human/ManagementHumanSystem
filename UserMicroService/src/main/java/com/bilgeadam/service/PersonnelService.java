package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateAdvanceRequestDto;
import com.bilgeadam.dto.request.CreateExpenseRequestDto;
import com.bilgeadam.dto.request.CreateLeaveRequestDto;
import com.bilgeadam.dto.request.UpdatePersonnelRequestDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.IAdvanceMapper;
import com.bilgeadam.mapper.IExpenseMapper;
import com.bilgeadam.mapper.ILeaveMapper;
import com.bilgeadam.rabbitmq.model.AddAuthIdModel;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.Advance;
import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.entity.Leave;
import com.bilgeadam.repository.entity.Personnel;
import com.bilgeadam.repository.enums.EApprovalStatus;
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
    private final AdvanceService advanceService;
    private final ExpenseService expenseService;

    public PersonnelService(IPersonnelRepository iPersonnelRepository, JwtTokenManager tokenManager, LeaveService leaveService, AdvanceService advanceService, ExpenseService expenseService) {
        super(iPersonnelRepository);
        this.iPersonnelRepository = iPersonnelRepository;
        this.tokenManager = tokenManager;
        this.leaveService = leaveService;
        this.advanceService = advanceService;
        this.expenseService = expenseService;
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
    public Boolean createAdvanceRequest(CreateAdvanceRequestDto dto, String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<Personnel> personnel = iPersonnelRepository.findOptionalByAuthId(authId.get());
        if (personnel.isEmpty()) {
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }
        if (dto.getAdvanceAmount()>(personnel.get().getSalary()*3))
            throw new UserManagerException(EErrorType.INVALID_ADVANCE_AMOUNT);
        Advance advance = IAdvanceMapper.INSTANCE.toAdvance(dto);
        advance.setPersonnelId(personnel.get().getId());
        advanceService.save(advance);
        return true;
    }
    public Boolean createExpenseRequest(CreateExpenseRequestDto dto, String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<Personnel> personnel = iPersonnelRepository.findOptionalByAuthId(authId.get());
        if (personnel.isEmpty()) {
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }
        Expense expense = IExpenseMapper.INSTANCE.toExpense(dto);
        expense.setPersonnelId(personnel.get().getId());
        expenseService.save(expense);
        return true;
    }


    public Boolean createAuthId(AddAuthIdModel model) {
        System.err.println(model.getAuthId());
        Optional<Personnel> personnel = iPersonnelRepository.findOptionalByEmail(model.getEmail());
        if(!personnel.isPresent())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        personnel.get().setAuthId(model.getAuthId());
        update(personnel.get());
        return true;
    }


    public List<Leave> findAllLeaveRequests (String token) {
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
                        (Leave a) -> a.getEApprovalStatus() == EApprovalStatus.PENDINGAPPROVAL ? 0 : 1).reversed()
                        // sort by status, with PENDING first
                .thenComparingLong(Leave::getCreateat).reversed())// then sort by descending createat
                //.sorted(Comparator.comparingLong(Leave::getCreateat).reversed())
                .forEach(x -> {
                    leaveList2.add(x);
                });
        return leaveList2;
    }
    public List<Advance> findAllAdvanceRequests (String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        System.out.println(authId.get());
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        List<Advance> advancelist = advanceService.findAll();
        if (advancelist.size()==0)
            throw new UserManagerException(EErrorType.ADVANCE_NOT_FOUND);
        //---------------Buraya filtreleme eklemeliyiz
        List<Advance> advanceList2 = new ArrayList<>();
        Optional<Personnel> personnel= iPersonnelRepository.findOptionalByAuthId(authId.get());

        if(personnel.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);

        advancelist.stream()
                .filter(a-> a.getPersonnelId()==personnel.get().getId())
                .sorted(Comparator.comparing(
                                (Advance a) -> a.getEApprovalStatus() == EApprovalStatus.PENDINGAPPROVAL ? 0 : 1).reversed()
                        // sort by status, with PENDING first
                        .thenComparingLong(Advance::getCreateat).reversed())// then sort by descending createat
                //.sorted(Comparator.comparingLong(Advance::getCreateat).reversed())
                .forEach(x -> {
                    advanceList2.add(x);
                });
        return advanceList2;
    }
    public List<Expense> findAllExpenseRequests (String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        System.out.println(authId.get());
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        List<Expense> expenselist = expenseService.findAll();
        if (expenselist.size()==0)
            throw new UserManagerException(EErrorType.EXPENSE_NOT_FOUND);
        //---------------Buraya filtreleme eklemeliyiz
        List<Expense> expenseList2 = new ArrayList<>();
        Optional<Personnel> personnel= iPersonnelRepository.findOptionalByAuthId(authId.get());

        if(personnel.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);

        expenselist.stream()
                .filter(a-> a.getPersonnelId()==personnel.get().getId())
                .sorted(Comparator.comparing(
                                (Expense a) -> a.getEApprovalStatus() == EApprovalStatus.PENDINGAPPROVAL ? 0 : 1).reversed()
                        // sort by status, with PENDING first
                        .thenComparingLong(Expense::getCreateat).reversed())// then sort by descending createat
                //.sorted(Comparator.comparingLong(Expense::getCreateat).reversed())
                .forEach(x -> {
                    expenseList2.add(x);
                });
        return expenseList2;
    }
}
