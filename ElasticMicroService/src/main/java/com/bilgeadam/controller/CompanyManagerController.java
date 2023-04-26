package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AddAdminRequestDto;
import com.bilgeadam.dto.request.AddCompanyManagerRequestDto;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.service.CompanyManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.constants.EndPoints.*;

@RestController
@RequestMapping(ELASTIC+COMPANYMANAGER)
@RequiredArgsConstructor
public class CompanyManagerController {

    private final CompanyManagerService companyManagerService;
    @PostMapping(SAVE)
    public ResponseEntity<Void> addCompanyManager (@RequestBody AddCompanyManagerRequestDto dto) {
        companyManagerService.saveDto(dto);
        return ResponseEntity.ok().build();
    }
}
