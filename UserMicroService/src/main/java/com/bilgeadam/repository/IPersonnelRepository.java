package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonnelRepository extends JpaRepository<Personnel,Long> {

    Optional<Personnel> findOptionalByAuthId(Long authId);

    Optional<Personnel> findOptionalById(Long id);
    Optional<Personnel> findOptionalByEmail(String email);
    @Query(value = "select COUNT(a)>0 from Admin a where a.email=?1")
    boolean isEmail(String email);
}
