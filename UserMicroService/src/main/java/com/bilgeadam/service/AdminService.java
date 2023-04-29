package com.bilgeadam.service;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.dto.response.CompanySummaryResponseDto;
import com.bilgeadam.dto.response.PersonnelSummaryResponseDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.manager.IElasticServiceAdminManager;
import com.bilgeadam.mapper.IAdminMapper;
import com.bilgeadam.mapper.ICompanyManagerMapper;
import com.bilgeadam.mapper.ICompanyMapper;
import com.bilgeadam.mapper.IPersonnelMapper;
import com.bilgeadam.rabbitmq.model.ChangeStatusModel;
import com.bilgeadam.rabbitmq.model.CreatePersonMailModel;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.rabbitmq.producer.CreatePersonProducer;
import com.bilgeadam.rabbitmq.producer.PasswordMailProducer;
import com.bilgeadam.repository.IAdminRepository;
import com.bilgeadam.repository.ICompanyManagerRepository;
import com.bilgeadam.repository.ICompanyRepository;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Personnel;
import com.bilgeadam.repository.enums.ERole;
import com.bilgeadam.utility.CodeGenerator;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;
import com.bilgeadam.utility.CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService extends ServiceManager<Admin,Long> {
    private final IAdminRepository repository;
    private final ICompanyRepository companyRepository;
    private final ICompanyManagerRepository companyManagerRepository;
    private final IPersonnelRepository personnelRepository;
    private final JwtTokenManager tokenManager;
    private final CompanyManagerService companyManagerService;
    private final CompanyService companyService;
    private final PersonnelService personnelService;
    private final IElasticServiceAdminManager elasticServiceAdminManager;
    private final CreatePersonProducer createPersonProducer;
    private final PasswordMailProducer passwordMailProducer;

    public AdminService(IAdminRepository repository, ICompanyRepository companyRepository, ICompanyManagerRepository companyManagerRepository, IPersonnelRepository personnelRepository, JwtTokenManager tokenManager, CompanyManagerService companyManagerService, CompanyService companyService, PersonnelService personnelService, IElasticServiceAdminManager elasticServiceAdminManager, CreatePersonProducer createPersonProducer, PasswordMailProducer passwordMailProducer) {
        super(repository);
        this.repository = repository;
        this.companyRepository = companyRepository;
        this.companyManagerRepository = companyManagerRepository;
        this.personnelRepository = personnelRepository;
        this.tokenManager = tokenManager;
        this.companyManagerService = companyManagerService;
        this.companyService = companyService;
        this.personnelService = personnelService;
        this.elasticServiceAdminManager = elasticServiceAdminManager;
        this.createPersonProducer = createPersonProducer;
        this.passwordMailProducer = passwordMailProducer;

    }

    public Boolean saveDto(AdminSaveRequestDto dto) {
        save(IAdminMapper.INSTANCE.toAdmin(dto));
        return true;
    }
    public Boolean createAdmin(RegisterModel model) {
//        Optional<Admin> admn = repository.findOptionalByEmail(model.getEmail());
//        if(!admn.isEmpty())
//            throw new UserManagerException(EErrorType.EMAIL_DUPLICATE);
        try {
            Admin admin = IAdminMapper.INSTANCE.toAdminProfile(model);
            save(admin);
            try {
                elasticServiceAdminManager.addAdmin(admin);
            } catch (Exception e) {
                throw new UserManagerException(EErrorType.USER_NOT_CREATED);
            }
        } catch (Exception e) {

        }
        return true;
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
            companyManagerService.save(companyManager);

            passwordMailProducer.sendPassword(mailModel);
            createPersonProducer.sendNewPerson(model);
            return true;
        } catch (Exception e) {
            throw new UserManagerException(EErrorType.COMPANY_MANAGER_NOT_CREATED);
        }
    }
    public Boolean createCompany(CompanySaveRequestDto dto) {
        Optional<CompanyManager> cM = companyManagerRepository.findOptionalByEmail(dto.getEmail());
        if(cM.isPresent())
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);

        Optional<Personnel> personnel = personnelRepository.findOptionalByEmail(dto.getEmail());
        if(personnel.isPresent())
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);

        Optional<Company> company1 = companyRepository.findOptionalByEmail(dto.getEmail());
        if(company1.isPresent())
            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);
//        Optional<Company> com = companyRepository.findOptionalByEmail(dto.getEmail());
//        if(!com.isEmpty())
//            throw new UserManagerException(EErrorType.EMAIL_DUPLICATE);
//        if (companyRepository.isEmail(dto.getEmail()))
//            throw new UserManagerException(EErrorType.REGISTER_ERROR_EMAIL);
        try {
            Company company = companyService.save(ICompanyMapper.INSTANCE.toCompany(dto));
            companyService.save(company);
            return true;
        } catch (Exception e) {
            throw new UserManagerException(EErrorType.COMPANY_NOT_CREATED);
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
            Personnel personnel = personnelService.save(IPersonnelMapper.INSTANCE.toPersonnel(model));
            personnelService.save(personnel);
            createPersonProducer.sendNewPerson(model);
            passwordMailProducer.sendPassword(mailModel);
            return true;
        } catch (Exception e) {
            throw new UserManagerException(EErrorType.PERSONNEL_NOT_CREATED);
        }
    }
   public void changeUserStatus(ChangeStatusModel model) {
       Optional<Admin> adminProfile = repository.findOptionalByAuthId(model.getAuthId());
//       Optional<CompanyManager> companyManagerProfile = repository.findOptionalByAuthId(model.getAuthId());
//       Optional<Personnel> personnelProfile = repository.findOptionalByAuthId(model.getAuthId());
       if (adminProfile.isEmpty())
           throw new UserManagerException(EErrorType.USER_NOT_FOUND);
       adminProfile.get().setStatus(model.getStatus());
       update(adminProfile.get());
   }
    public Boolean updateAdmin(UpdateAdminRequestDto dto, Long id) {
            Optional<Long> authId = tokenManager.getIdFromToken(dto.getToken());
            if (authId.isEmpty()) {
                throw new UserManagerException(EErrorType.INVALID_TOKEN);
            }
            Optional<Admin> adminProfile = repository.findOptionalByAuthId(id);
            if (adminProfile.isEmpty()) {
                throw new UserManagerException(EErrorType.USER_NOT_FOUND);
            }
            adminProfile.get().setName(dto.getName());
            adminProfile.get().setNameSecond(dto.getNameSecond());
            adminProfile.get().setSurname(dto.getSurname());
            adminProfile.get().setSurnameSecond(dto.getSurnameSecond());
            adminProfile.get().setPhoneNumber(dto.getPhoneNumber());
            adminProfile.get().setPhoto(dto.getPhoto());
            adminProfile.get().setAddress(dto.getAddress());
            adminProfile.get().setBirthday(dto.getBirthday());
            adminProfile.get().setBirthPlace(dto.getBirthPlace());
            adminProfile.get().setTC(dto.getTC());
            update(adminProfile.get());
            return true;
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
        companyManagerService.update(companyManagerProfile.get());
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
        companyProfile.get().setContractStartDate(dto.getContractStartDate());
        companyProfile.get().setContractEndDate(dto.getContractEndDate());
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

//    public Object findAdminByIdWithToken(String token, Long id) {
//        Optional<Long> authId = tokenManager.getIdFromToken(token);
//        if (authId.isEmpty())
//            throw new UserManagerException(EErrorType.INVALID_TOKEN);
//        Optional<Admin> adminProfile = repository.findById(id);
//        Optional<Company> companyProfile = companyRepository.findOptionalById(id);
//        Optional<CompanyManager> companyManagerProfile = companyManagerRepository.findOptionalById(id);
//        Optional<Personnel> personnelProfile = personnelRepository.findOptionalById(id);
//        if (adminProfile.isEmpty() && companyProfile.isEmpty() && companyManagerProfile.isEmpty() && personnelProfile.isEmpty())
//            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
//        else if(adminProfile.isEmpty() && companyProfile.isEmpty() && companyManagerProfile.isEmpty())
//            return personnelProfile.get();
//        else if(adminProfile.isEmpty() && companyProfile.isEmpty() && personnelProfile.isEmpty())
//            return companyManagerProfile.get();
//        else if(adminProfile.isEmpty() && companyManagerProfile.isEmpty() && personnelProfile.isEmpty())
//            return companyProfile.get();
//        else
//            return adminProfile.get();
//    }

    public Admin findAdminByIdWithToken(String token, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<Admin> userProfile = repository.findById(id);
        if (userProfile.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        return userProfile.get();
    }
    public Company findCompanyByIdWithToken(String token, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<Company> userProfile = companyRepository.findById(id);
        if (userProfile.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        return userProfile.get();
    }
    public CompanyManager findCompanyManagerByIdWithToken(String token, Long id) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<CompanyManager> userProfile = companyManagerRepository.findById(id);
        if (userProfile.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        return userProfile.get();
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
    public List<AdminSummaryResponseDto> findAllSummaryAdmin(String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        List<Admin> adminList = findAll();
        List<AdminSummaryResponseDto> adminSummaryResponseDtoList = new ArrayList<>();
        adminList.forEach(x -> {
                adminSummaryResponseDtoList.add(IAdminMapper.INSTANCE.toAdminProfileSummaryResponse(x));
            });
        return adminSummaryResponseDtoList;
    }
    public List<CompanySummaryResponseDto> findAllSummaryCompany(String token) {
        Optional<Long> authId = tokenManager.getIdFromToken(token);
        if (authId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        List<Company> companyList = companyService.findAll();
        List<CompanySummaryResponseDto> companySummaryResponseDtoList = new ArrayList<>();
        companyList.forEach(x -> {
            companySummaryResponseDtoList.add(ICompanyMapper.INSTANCE.toCompanyProfileSummaryResponse(x));
        });
        return companySummaryResponseDtoList;
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
        List<CompanyManager> companyManagerList = companyManagerService.findAll();
        List<CompanyManagerSummaryResponseDto> companyManagerSummaryResponseDtoList = new ArrayList<>();
        companyManagerList.forEach(x -> {
            companyManagerSummaryResponseDtoList.add(ICompanyManagerMapper.INSTANCE.toCompanyManagerProfileSummaryResponse(x));
        });
        return companyManagerSummaryResponseDtoList;
    }

//    public List<AdminSummaryResponseDto> findAllSummary(String tur,String token) {
//        Optional<Long> authId = tokenManager.getIdFromToken(token);
//        if (authId.isEmpty())
//            throw new UserManagerException(EErrorType.INVALID_TOKEN);
//
//        List<Admin> adminList = findAll();
//        List<Company> companyList = companyService.findAll();
//        List<CompanyManager> companyManagerList = companyManagerService.findAll();
//        List<Personnel> personnelList = personnelService.findAll();
//        List<AdminSummaryResponseDto> adminSummaryResponseDtoList = new ArrayList<>();
//        List<CompanySummaryResponseDto> companySummaryResponseDtoList = new ArrayList<>();
//        List<CompanyManagerSummaryResponseDto> companyManagerSummaryResponseDtoList = new ArrayList<>();
//        List<PersonnelSummaryResponseDto> personnelSummaryResponseDtoList = new ArrayList<>();
////        if(tur.equals("hepsi")) {
////
////            adminList.forEach(x -> {
////                adminSummaryResponseDtoList.add(IAdminMapper.INSTANCE.toAdminProfileSummaryResponse(x));
////            });
////            companyList.forEach(x -> {
////                adminSummaryResponseDtoList.add(ICompanyMapper.INSTANCE.toCompanyProfileSummaryResponse(x));
////            });
////            companyManagerList.forEach(x -> {
////                adminSummaryResponseDtoList.add(ICompanyManagerMapper.INSTANCE.toCompanyManagerProfileSummaryResponse(x));
////            });
////            personnelList.forEach(x -> {
////                adminSummaryResponseDtoList.add(IPersonnelMapper.INSTANCE.toPersonnelProfileSummaryResponse(x));
////            });
////            return adminSummaryResponseDtoList;
////        }
//        if(tur.equals("admin")){
//            adminList.forEach(x -> {
//                adminSummaryResponseDtoList.add(IAdminMapper.INSTANCE.toAdminProfileSummaryResponse(x));
//            });
//            return adminSummaryResponseDtoList;
//        }
//        else if(tur.equals("company")){
//            companyList.forEach(x -> {
//                companySummaryResponseDtoList.add(ICompanyMapper.INSTANCE.toCompanyProfileSummaryResponse(x));
//            });
//            return adminSummaryResponseDtoList;
//        }
//        else if(tur.equals("personnel")){
//            personnelList.forEach(x -> {
//                personnelSummaryResponseDtoList.add(IPersonnelMapper.INSTANCE.toPersonnelProfileSummaryResponse(x));
//            });
//            return adminSummaryResponseDtoList;
//        }
//        else if(tur.equals("companymanager")){
//            companyManagerList.forEach(x -> {
//                companyManagerSummaryResponseDtoList.add(ICompanyManagerMapper.INSTANCE.toCompanyManagerProfileSummaryResponse(x));
//            });
//            return adminSummaryResponseDtoList;
//        }
//        throw new UserManagerException(EErrorType.TUR_NOT_FOUND);
//    }



}
