package com.bilgeadam.service;

import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.Personnel;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class PersonnelService extends ServiceManager<Personnel,Long> {

    private final IPersonnelRepository iPersonnelRepository;
    private final JwtTokenManager tokenManager;

    public PersonnelService(IPersonnelRepository iPersonnelRepository, JwtTokenManager tokenManager) {
        super(iPersonnelRepository);
        this.iPersonnelRepository = iPersonnelRepository;
        this.tokenManager = tokenManager;
    }


}
