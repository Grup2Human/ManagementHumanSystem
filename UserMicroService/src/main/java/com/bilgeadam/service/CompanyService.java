package com.bilgeadam.service;

import com.bilgeadam.repository.ICompanyRepository;
import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends ServiceManager<Company,Long> {

    private final ICompanyRepository companyRepository;
    private final JwtTokenManager tokenManager;

    public CompanyService( ICompanyRepository companyRepository, JwtTokenManager tokenManager) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.tokenManager = tokenManager;
    }


}
