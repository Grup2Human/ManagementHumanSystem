package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.CompanyManager;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyManagerRepository extends ElasticsearchRepository<CompanyManager,String> {

    boolean existsByCompanyManagerId(Long id);
    Optional<CompanyManager> findOptionalByAuthId(Long authId);
    Optional<CompanyManager> findOptionalById(Long id);
}
