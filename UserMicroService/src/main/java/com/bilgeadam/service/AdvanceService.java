package com.bilgeadam.service;

import com.bilgeadam.repository.IAdvanceRepository;
import com.bilgeadam.repository.entity.*;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AdvanceService extends ServiceManager<Advance,Long> {
    private final IAdvanceRepository advanceRepository;
    private final JwtTokenManager tokenManager;
    public AdvanceService(IAdvanceRepository advanceRepository, JwtTokenManager tokenManager) {
        super(advanceRepository);
        this.advanceRepository = advanceRepository;
        this.tokenManager = tokenManager;
    }
}
