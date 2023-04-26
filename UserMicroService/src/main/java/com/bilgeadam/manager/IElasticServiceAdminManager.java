package com.bilgeadam.manager;

import com.bilgeadam.repository.entity.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.bilgeadam.constants.EndPoints.SAVE;
@FeignClient(name = "elastic-service-admin-manager",          //isim benzersiz olmalı
url = "http://localhost:9100/elastic/admin",decode404 = true)
public interface IElasticServiceAdminManager {
    @PostMapping(SAVE)
    public ResponseEntity<Void> addAdmin(@RequestBody Admin dto);
}
