package com.bilgeadam.service;

import com.bilgeadam.repository.IExpenseRepository;
import com.bilgeadam.repository.ILeaveRepository;
import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.entity.Leave;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService extends ServiceManager<Expense,Long> {
    private final IExpenseRepository expenseRepository;
    private final JwtTokenManager tokenManager;
    public ExpenseService(IExpenseRepository expenseRepository, JwtTokenManager tokenManager) {
        super(expenseRepository);
        this.expenseRepository = expenseRepository;
        this.tokenManager = tokenManager;
    }
}
