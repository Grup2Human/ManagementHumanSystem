package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.repository.entity.CompanyManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyManagerRepository extends JpaRepository<CompanyManager,Long> {

    Optional<CompanyManager> findOptionalByAuthId(Long authId);
    Optional<CompanyManager> findOptionalByEmail(String email);
    @Query(value = "select COUNT(a.email)>0 from Admin a where a.email=?1")
    boolean isEmail(String email);

    Optional<CompanyManager> findOptionalById(Long id);
}
