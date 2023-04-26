package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Admin;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IAdminRepository extends ElasticsearchRepository<Admin,String> {
    boolean existsByAdminId(Long id);
//    /**
//     * Bu kullanıcı adı daha önce kullanılmış mı?
//     * @param username
//     * @return
//     */
//
//    /**
//     * Kullanıcı adı ve şifresi verilen kaydın olup olmadığı kontol ediliyor.
//     * @param username
//     * @param password
//     * @return
//     */
    Optional<Admin> findOptionalByAuthId(Long authId);

}
