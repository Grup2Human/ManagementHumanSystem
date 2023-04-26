package com.bilgeadam.service;

import com.bilgeadam.dto.request.AddAdminRequestDto;
import com.bilgeadam.mapper.IAdminMapper;
import com.bilgeadam.repository.IAdminRepository;
import com.bilgeadam.repository.ICompanyManagerRepository;
import com.bilgeadam.repository.ICompanyRepository;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends ServiceManager<Admin,String> {
    private final IAdminRepository repository;
    private final ICompanyRepository companyRepository;
    private final ICompanyManagerRepository companyManagerRepository;
    private final IPersonnelRepository personnelRepository;
    private final JwtTokenManager tokenManager;
    private final CompanyManagerService companyManagerService;
    private final CompanyService companyService;
    private final PersonnelService personnelService;

    public AdminService(IAdminRepository repository, ICompanyRepository companyRepository, ICompanyManagerRepository companyManagerRepository, IPersonnelRepository personnelRepository, JwtTokenManager tokenManager, CompanyManagerService companyManagerService, CompanyService companyService, PersonnelService personnelService) {
        super(repository);
        this.repository = repository;
        this.companyRepository = companyRepository;
        this.companyManagerRepository = companyManagerRepository;
        this.personnelRepository = personnelRepository;
        this.tokenManager = tokenManager;
        this.companyManagerService = companyManagerService;
        this.companyService = companyService;
        this.personnelService = personnelService;
    }

    public void saveDto(AddAdminRequestDto dto) {
        /**
         * Eğer userid daha önceden kayıt edilmiş ise kaydetme işlemi yapma
         */
        if(!repository.existsByAdminId(dto.getId()))
            save(IAdminMapper.INSTANCE.toAdmin(dto));
    }
}
