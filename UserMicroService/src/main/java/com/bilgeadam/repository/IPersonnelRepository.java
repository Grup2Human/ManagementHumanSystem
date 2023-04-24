package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonnelRepository extends JpaRepository<Personnel,Long> {
}
