package com.bilgeadam.manager;

import com.bilgeadam.repository.entity.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.bilgeadam.constants.EndPoints.SAVE;

@FeignClient(name = "elastic-service-company-manager",          //isim benzersiz olmalı
url = "http://localhost:9100/elastic/company",decode404 = true)
public interface IElasticServiceCompanyManager {
    @PostMapping(SAVE)
    public ResponseEntity<Void> addCompany (@RequestBody Company dto);
}
