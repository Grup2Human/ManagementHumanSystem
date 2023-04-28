package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IAdminRepository extends JpaRepository<Admin,Long> {
//    /**
//     * Bu kullanıcı adı daha önce kullanılmış mı?
//     * @param username
//     * @return
//     */
    @Query(value = "select COUNT(a)>0 from Admin a where a.email=?1")
    boolean isEmail(String email);
//
//    /**
//     * Kullanıcı adı ve şifresi verilen kaydın olup olmadığı kontol ediliyor.
//     * @param username
//     * @param password
//     * @return
//     */
    Optional<Admin> findOptionalByAuthId(Long authId);
    Optional<Admin> findOptionalByEmail(String email);

}
