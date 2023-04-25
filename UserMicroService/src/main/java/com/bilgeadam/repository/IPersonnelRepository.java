package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonnelRepository extends JpaRepository<Personnel,Long> {

    Optional<Personnel> findOptionalByAuthId(Long authId);
}
