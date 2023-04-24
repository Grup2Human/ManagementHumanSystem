package com.bilgeadam.service;

import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.ICompanyManagerMapper;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.ICompanyManagerRepository;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CompanyManagerService extends ServiceManager<CompanyManager,Long> {

    private final ICompanyManagerRepository companyManagerRepository;
    private final JwtTokenManager tokenManager;


    public CompanyManagerService(ICompanyManagerRepository companyManagerRepository, JwtTokenManager tokenManager) {
        super(companyManagerRepository);
        this.companyManagerRepository = companyManagerRepository;
        this.tokenManager = tokenManager;

    }

    public Boolean createCompanyManager(RegisterModel model) {
        try {
            CompanyManager companyManager = save(ICompanyManagerMapper.INSTANCE.toCompanyManager(model));
            save(companyManager);
            return true;
        } catch (Exception e) {
            throw new UserManagerException(EErrorType.USER_NOT_CREATED);
        }
    }



}
