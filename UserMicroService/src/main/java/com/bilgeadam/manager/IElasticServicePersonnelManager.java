package com.bilgeadam.manager;

import com.bilgeadam.repository.entity.Personnel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.bilgeadam.constants.EndPoints.SAVE;

@FeignClient(name = "elastic-service-personel-manager",          //isim benzersiz olmalı
url = "http://localhost:9100/elastic/personnel",decode404 = true)
public interface IElasticServicePersonnelManager {
    @PostMapping(SAVE)
    public ResponseEntity<Void> addPersonnel(@RequestBody Personnel dto);
}
