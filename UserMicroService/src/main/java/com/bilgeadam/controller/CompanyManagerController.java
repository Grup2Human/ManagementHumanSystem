package com.bilgeadam.controller;

import com.bilgeadam.dto.request.UpdateAdminRequestDto;
import com.bilgeadam.dto.request.UpdateCompanyManagerRequestDto;
import com.bilgeadam.dto.request.UpdatePersonnelRequestDto;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.service.CompanyManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.EndPoints.*;

@RestController
@RequestMapping(COMPANYMANAGER)
@RequiredArgsConstructor
public class CompanyManagerController {

    private final CompanyManagerService companyManagerService;
    @PutMapping(UPDATECOMPANYMANAGER)
    public ResponseEntity<Boolean> updateCompanyManager(@RequestBody UpdateCompanyManagerRequestDto dto) {
        return ResponseEntity.ok(companyManagerService.updateCompanyManager(dto));
    }
    @PutMapping(UPDATEPERSONNEL)
    public ResponseEntity<Boolean> updatePersonnel(@RequestBody UpdatePersonnelRequestDto dto) {
        return ResponseEntity.ok(companyManagerService.updatePersonnel(dto));
    }
//    @GetMapping(GETALL)
//    public ResponseEntity<List<CompanyManagerSummaryResponseDto>> findAll() {
//        return ResponseEntity.ok(companyManagerService.findAllSummary());
//    }

    @GetMapping(GETCOMPANYMANAGERBYID)
    public ResponseEntity<CompanyManager> findById(String token, Long id) {
        return ResponseEntity.ok(companyManagerService.findByIdWithToken(token,id));
    }
}
