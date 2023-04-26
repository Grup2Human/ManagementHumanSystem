package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.repository.entity.CompanyManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyManagerRepository extends JpaRepository<CompanyManager,Long> {

    Optional<CompanyManager> findOptionalByAuthId(Long authId);


    Optional<CompanyManager> findOptionalById(Long id);
}
