package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Company;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ICompanyRepository extends ElasticsearchRepository<Company,String> {

    boolean existsByCompanyId(Long id);
    Optional<Company> findOptionalById(Long companyId);
}
