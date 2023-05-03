package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense,Long> {
}
