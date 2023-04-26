package com.bilgeadam.manager;

import com.bilgeadam.repository.entity.CompanyManager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.bilgeadam.constants.EndPoints.SAVE;

@FeignClient(name = "elastic-service-companymanager-manager",          //isim benzersiz olmalı
url = "http://localhost:9100/elastic/companymanager",decode404 = true)
public interface IElasticServiceCompanyManagerManager {
    @PostMapping(SAVE)
    public ResponseEntity<Void> addCompanyManager(@RequestBody CompanyManager dto);
}
