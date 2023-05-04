package com.bilgeadam.service;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.dto.response.LeaveDemandsResponseDto;
import com.bilgeadam.dto.response.PersonnelSummaryResponseDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.ICompanyManagerMapper;
import com.bilgeadam.mapper.ILeaveMapper;
import com.bilgeadam.mapper.IPersonnelMapper;
import com.bilgeadam.rabbitmq.model.AddAuthIdModel;
import com.bilgeadam.rabbitmq.model.CreatePersonMailModel;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.rabbitmq.producer.CreatePersonProducer;
import com.bilgeadam.rabbitmq.producer.PasswordMailProducer;
import com.bilgeadam.repository.ICompanyManagerRepository;
import com.bilgeadam.repository.ICompanyRepository;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.*;
import com.bilgeadam.repository.enums.EApprovalStatus;
import com.bilgeadam.repository.enums.ERole;
import com.bilgeadam.utility.CodeGenerator;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyManagerService extends ServiceManager<CompanyManager,Long> {

    private final ICompanyManagerRepository companyManagerRepository;
    private final JwtTokenManager tokenManager;
    private final ICompanyRepository companyRepository;
    private final CompanyService companyService;
    private final IPersonnelRepository personnelRepository;
    private final PersonnelService personnelService;
    private final CreatePersonProducer createPersonProducer;
    private final PasswordMailProducer passwordMailProducer;
    private final LeaveService leaveService;
    private final AdvanceService advanceService;
    private final ExpenseService expenseService;



    public CompanyManagerService(ICompanyManagerRepository companyManagerRepository, JwtTokenManager tokenManager, ICompanyRepository companyRepository, CompanyService companyService, IPersonnelRepository personnelRepository, PersonnelService personnelService, CreatePersonProducer createPersonProducer, PasswordMailProducer passwordMailProducer, LeaveService leaveService, AdvanceService advanceService, ExpenseService expenseService) {
        super(companyManagerRepository);
        this.companyManagerRepository = companyManagerRepository;
        this.tokenManager = tokenManager;
        this.companyRepository = companyRepository;
        this.companyService = companyService;
        this.personnelRepository = personnelRepository;
        this.personnelService = personnelService;
        this.createPersonProducer = createPersonProducer;
        this.passwordMailProducer = passwordMailProducer;
        this.leaveService = leaveService;
        this.advanceService = advanceService;
        this.expenseService = expenseService;
    }

    public Boolean updateCompanyManager(UpdateCompanyManagerRequestDto dto, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<CompanyManager> companyManagerProfile = companyManagerRepository.findById(id);
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
    public Boolean updateCompany(UpdateCompanyRequestDto dto, Long id) {
        Optional<Long> companyId = tokenManager.getIdFromToken(dto.getToken());
        if (companyId.isEmpty()) {
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<Company> companyProfile = companyRepository.findOptionalById(id);
        if (companyProfile.isEmpty()) {
            throw new UserManagerException(EErrorType.COMPANY_NOT_FOUND);
        }
        companyProfile.get().setCompanyName(dto.getCompanyName());
        companyProfile.get().setCompanyType(dto.getCompanyType());
        companyProfile.get().setCentralRegistrationSystemNo(dto.getCentralRegistrationSystemNo());
        companyProfile.get().setTaxId(dto.getTaxId());
        companyProfile.get().setTaxOffice(dto.getTaxOffice());
        companyProfile.get().setEmail(dto.getEmail());
        companyProfile.get().setPhone(dto.getPhone());
        companyProfile.get().setPhone(dto.getPhone());
        companyProfile.get().setFoundationYear(dto.getFoundationYear());
        companyProfile.get().setLogo(dto.getLogo());
        companyProfile.get().setHeadcount(dto.getHeadcount());
        companyService.update(companyProfile.get());
        return true;
    }
    public Boolean updatePersonnel(UpdatePersonnelRequestDto dto, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<Personnel> personnelProfile = personnelRepository.findById(id);
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
    public CompanyManager findCompanyManagerByIdWithToken(String token, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<CompanyManager> companyManager = companyManagerRepository.findById(id);
        if (companyManager.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        return companyManager.get();
    }
    public Personnel findPersonnelByIdWithToken(String token, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<Personnel> userProfile = personnelRepository.findById(id);
        if (userProfile.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        return userProfile.get();
    }
    public List<PersonnelSummaryResponseDto> findAllSummaryPersonnel(String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        List<Personnel> adminList = personnelService.findAll();
        List<PersonnelSummaryResponseDto> personnelSummaryResponseDtoList = new ArrayList<>();
        adminList.forEach(x -> {
            personnelSummaryResponseDtoList.add(IPersonnelMapper.INSTANCE.toPersonnelProfileSummaryResponse(x));
        });
        return personnelSummaryResponseDtoList;
    }
    public List<CompanyManagerSummaryResponseDto> findAllSummaryCompanyManager(String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        List<CompanyManager> companyManagerList = findAll();
        List<CompanyManagerSummaryResponseDto> companyManagerSummaryResponseDtoList = new ArrayList<>();
        companyManagerList.forEach(x -> {
            companyManagerSummaryResponseDtoList.add(ICompanyManagerMapper.INSTANCE.toCompanyManagerProfileSummaryResponse(x));
        });
        return companyManagerSummaryResponseDtoList;
    }
    public Boolean createCompanyManager(CompanyManagerSaveRequestDto dto) {
        Optional<CompanyManager> cM = companyManagerRepository.findOptionalByEmail(dto.getEmail());
        if(cM.isPresent())
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);

        Optional<Personnel> personnel = personnelRepository.findOptionalByEmail(dto.getEmail());
        if(personnel.isPresent())
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);

        Optional<Company> company = companyRepository.findOptionalByEmail(dto.getEmail());
        if(company.isPresent())
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);

//        if (companyManagerRepository.isEmail(dto.getEmail()))
//            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);

        try {
            CreatePersonModel model = CreatePersonModel.builder()
                    .email(dto.getEmail())
                    .password(CodeGenerator.generateCode())
                    .role(ERole.COMPANYMANAGER)
                    .build();


            CreatePersonMailModel mailModel = CreatePersonMailModel.builder()
                    .email(dto.getEmail())
                    .password(model.getPassword())
                    .build();

            CompanyManager companyManager = ICompanyManagerMapper.INSTANCE.toCompanyManager(model);
            save(companyManager);

            passwordMailProducer.sendPassword(mailModel);
            createPersonProducer.sendNewPerson(model);
            return true;
        } catch (Exception e) {
            throw new UserManagerException(EErrorType.COMPANY_MANAGER_NOT_CREATED);
        }
    }
    public Boolean createPersonnel(PersonnelSaveRequestDto dto) {
        Optional<CompanyManager> cM = companyManagerRepository.findOptionalByEmail(dto.getEmail());
        if(cM.isPresent())
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);

        Optional<Personnel> personnel1 = personnelRepository.findOptionalByEmail(dto.getEmail());
        if(personnel1.isPresent())
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);

        Optional<Company> company = companyRepository.findOptionalByEmail(dto.getEmail());
        if(company.isPresent())
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);
//        Optional<Personnel> prs = personnelRepository.findOptionalByEmail(dto.getEmail());
//        if(!prs.isEmpty())
//            throw new UserManagerException(EErrorType.EMAIL_DUPLICATE);
        if (personnelRepository.isEmail(dto.getEmail()))
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);
        try {
            CreatePersonModel model = CreatePersonModel.builder()
                    .email(dto.getEmail())
                    .password(CodeGenerator.generateCode())
                    .role(ERole.PERSONNEL)
                    .build();

            CreatePersonMailModel mailModel = CreatePersonMailModel.builder()
                    .email(dto.getEmail())
                    .password(model.getPassword())
                    .build();

            Personnel personnel = IPersonnelMapper.INSTANCE.toPersonnel(model);
            personnel.setSalary(dto.getSalary());
            personnelService.save(personnel);
            createPersonProducer.sendNewPerson(model);
            passwordMailProducer.sendPassword(mailModel);
            return true;
        } catch (Exception e) {
            throw new UserManagerException(EErrorType.PERSONNEL_NOT_CREATED);
        }
    }

    public List<LeaveDemandsResponseDto> findAllLeaveRequests(String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        System.out.println(authId.get());
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        List<Leave> leavelist = leaveService.findAll();
        if (leavelist.size() == 0)
            throw new UserManagerException(EErrorType.LEAVE_NOT_FOUND);
        //---------------Buraya filtreleme eklemeliyiz
        List<LeaveDemandsResponseDto> leaveDemandsResponseDtoList = new ArrayList<>();
        leavelist.stream()
                .filter(a -> a.getCompanyManagerId() == authId.get() && a.getEApprovalStatus().equals(EApprovalStatus.PENDINGAPPROVAL))
                .sorted(Comparator.comparingLong(Leave::getCreateat)) // sort by descending createat
                .forEach(x -> {
                    leaveDemandsResponseDtoList.add(ILeaveMapper.INSTANCE.todemandsResponseDto(x));
                });
        return leaveDemandsResponseDtoList;
    }

      public Boolean createAuthId(AddAuthIdModel model) {
          System.out.println(model.getAuthId());
          System.out.println("------------------------------");
          Optional<CompanyManager> companyManager = companyManagerRepository.findOptionalByEmail(model.getEmail());
          if(companyManager.isEmpty())
              throw new UserManagerException(EErrorType.USER_NOT_FOUND);
          companyManager.get().setAuthId(model.getAuthId());
              update(companyManager.get());

        return true;
      }

    public List<Advance> findAllAdvanceRequests(String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        System.out.println(authId.get());
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        List<Advance> advancelist = advanceService.findAll();
        if (advancelist.size() == 0)
            throw new UserManagerException(EErrorType.LEAVE_NOT_FOUND);
        //---------------Buraya filtreleme eklemeliyiz
        List<Advance> advanceList2 = new ArrayList<>();
        advancelist.stream()
                .filter(a -> a.getCompanyManagerId() == authId.get() && a.getEApprovalStatus().equals(EApprovalStatus.PENDINGAPPROVAL))
                .sorted(Comparator.comparingLong(Advance::getCreateat)) // sort by descending createat
                .forEach(x -> {
                    advanceList2.add(x);
                });
        return advanceList2;
    }
      public Boolean leaveApproval(String token,Long leaveId,String onay){
          Optional<Long> authId = tokenManager.getIdFromToken(token);
          if (authId.isEmpty())
              throw new UserManagerException(EErrorType.INVALID_TOKEN);

         Optional<Leave> leave =  leaveService.findById(leaveId);
         if (leave.isEmpty() ){
             throw new UserManagerException(EErrorType.LEAVE_NOT_FOUND);
         }
         if(!(leave.get().getCompanyManagerId()== authId.get()))
             throw new UserManagerException(EErrorType.UNAUTHORIZED_REQUEST);

         if(onay.equals("onaylandi")) {
             leave.get().setEApprovalStatus(EApprovalStatus.APPROVED);
             leave.get().setResponseDate(LocalDate.now());
             leaveService.update(leave.get());
             return true;
         }else{
             leave.get().setEApprovalStatus(EApprovalStatus.REJECTED);
             leave.get().setResponseDate(LocalDate.now());
             leaveService.update(leave.get());
             return false;
          }

      }
    public Boolean advanceApproval(String token,Long advanceId,String onay) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<Advance> advance = advanceService.findById(advanceId);
        if (advance.isEmpty()) {
            throw new UserManagerException(EErrorType.ADVANCE_NOT_FOUND);
        }
        if (!(advance.get().getCompanyManagerId() == authId.get()))
            throw new UserManagerException(EErrorType.UNAUTHORIZED_REQUEST);

        if (onay.equals("onaylandi")) {
            advance.get().setEApprovalStatus(EApprovalStatus.APPROVED);
            advance.get().setResponseDate(LocalDate.now());
            advanceService.update(advance.get());
            return true;
        } else {
            advance.get().setEApprovalStatus(EApprovalStatus.REJECTED);
            advance.get().setResponseDate(LocalDate.now());
            advanceService.update(advance.get());
            return false;
        }
    }
        public List<Expense> findAllExpenseRequests (String token) {
            Optional<Long> authId = tokenManager.getIdFromToken(token);
            System.out.println(authId.get());
            if (authId.isEmpty())
                throw new UserManagerException(EErrorType.INVALID_TOKEN);
            List<Expense> expenselist = expenseService.findAll();
            if (expenselist.size() == 0)
                throw new UserManagerException(EErrorType.LEAVE_NOT_FOUND);
            //---------------Buraya filtreleme eklemeliyiz
            List<Expense> expenseList2 = new ArrayList<>();
            expenselist.stream()
                    .filter(a -> a.getCompanyManagerId() == authId.get() && a.getEApprovalStatus().equals(EApprovalStatus.PENDINGAPPROVAL))
                    .sorted(Comparator.comparingLong(Expense::getCreateat)) // sort by descending createat
                    .forEach(x -> {
                        expenseList2.add(x);
                    });
            return expenseList2;
        }
    public Boolean expenseApproval(String token,Long expenseId,String onay) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<Expense> expense = expenseService.findById(expenseId);
        if (expense.isEmpty()) {
            throw new UserManagerException(EErrorType.EXPENSE_NOT_FOUND);
        }
        if (!(expense.get().getCompanyManagerId() == authId.get()))
            throw new UserManagerException(EErrorType.UNAUTHORIZED_REQUEST);

        if (onay.equals("onaylandi")) {
            expense.get().setEApprovalStatus(EApprovalStatus.APPROVED);
            expense.get().setResponseDate(LocalDate.now());
            expenseService.update(expense.get());
            return true;
        } else {
            expense.get().setEApprovalStatus(EApprovalStatus.REJECTED);
            expense.get().setResponseDate(LocalDate.now());
            expenseService.update(expense.get());
            return false;
        }
    }
        



}
