package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.repository.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ICompanyRepository extends JpaRepository<Company,Long> {


    Optional<Company> findOptionalById(Long companyId);
}
