package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.repository.entity.CompanyManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyManagerRepository extends JpaRepository<CompanyManager,Long> {
}
