package com.bilgeadam.service;

import com.bilgeadam.dto.request.AddAdminRequestDto;
import com.bilgeadam.dto.request.AddCompanyManagerRequestDto;
import com.bilgeadam.dto.request.AddCompanyRequestDto;
import com.bilgeadam.mapper.IAdminMapper;
import com.bilgeadam.mapper.ICompanyManagerMapper;
import com.bilgeadam.mapper.ICompanyMapper;
import com.bilgeadam.repository.ICompanyRepository;
import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends ServiceManager<Company,String> {

    private final ICompanyRepository companyRepository;
    private final JwtTokenManager tokenManager;

    public CompanyService( ICompanyRepository companyRepository, JwtTokenManager tokenManager) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.tokenManager = tokenManager;
    }
    public void saveDto(AddCompanyRequestDto dto) {
        /**
         * Eğer userid daha önceden kayıt edilmiş ise kaydetme işlemi yapma
         */
        if(!companyRepository.existsByCompanyId(dto.getId()))
            save(ICompanyMapper.INSTANCE.toCompany(dto));
    }




}
