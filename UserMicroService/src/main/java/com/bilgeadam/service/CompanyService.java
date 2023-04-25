package com.bilgeadam.service;

import com.bilgeadam.dto.request.UpdateCompanyRequestDto;
import com.bilgeadam.dto.request.UpdatePersonnelRequestDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.repository.ICompanyRepository;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.repository.entity.Personnel;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
