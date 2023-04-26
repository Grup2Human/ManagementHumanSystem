package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Personnel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonnelRepository extends ElasticsearchRepository<Personnel,String> {
    boolean existsByPersonnelId(Long id);
    Optional<Personnel> findOptionalByAuthId(Long authId);
    Optional<Personnel> findOptionalById(Long id);
}
