package com.bilgeadam.service;

import com.bilgeadam.dto.request.AddCompanyManagerRequestDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.IAdminMapper;
import com.bilgeadam.mapper.ICompanyManagerMapper;
import com.bilgeadam.repository.ICompanyManagerRepository;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Personnel;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyManagerService extends ServiceManager<CompanyManager,String> {

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

    public void saveDto(AddCompanyManagerRequestDto dto) {
        /**
         * Eğer userid daha önceden kayıt edilmiş ise kaydetme işlemi yapma
         */
        if(!companyManagerRepository.existsByCompanyManagerId(dto.getId()))
            save(ICompanyManagerMapper.INSTANCE.toCompanyManager(dto));
    }



}
