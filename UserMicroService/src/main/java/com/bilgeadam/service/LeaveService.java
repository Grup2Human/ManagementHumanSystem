package com.bilgeadam.service;

import com.bilgeadam.repository.ILeaveRepository;
import com.bilgeadam.repository.entity.Leave;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class LeaveService extends ServiceManager<Leave,Long> {
    private final ILeaveRepository leaveRepository;
    private final JwtTokenManager tokenManager;
    public LeaveService(ILeaveRepository leaveRepository, JwtTokenManager tokenManager) {
        super(leaveRepository);
        this.leaveRepository = leaveRepository;
        this.tokenManager = tokenManager;
    }
}
