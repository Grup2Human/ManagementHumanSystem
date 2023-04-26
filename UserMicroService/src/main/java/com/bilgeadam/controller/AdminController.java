package com.bilgeadam.controller;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Personnel;
import com.bilgeadam.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.EndPoints.*;

@RestController
@RequestMapping(ADMIN)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @PutMapping(UPDATEADMIN)
    public ResponseEntity<Boolean> updateAdmin(@RequestBody UpdateAdminRequestDto dto,Long id) {
        return ResponseEntity.ok(adminService.updateAdmin(dto,id));
    }
    @PutMapping(UPDATEPERSONNEL)
    public ResponseEntity<Boolean> updatePersonnel(@RequestBody UpdatePersonnelRequestDto dto,Long id) {
        return ResponseEntity.ok(adminService.updatePersonnel(dto,id));
    }
    @PutMapping(UPDATECOMPANYMANAGER)
    public ResponseEntity<Boolean> updateCompanyManager(@RequestBody UpdateCompanyManagerRequestDto dto,Long id) {
        return ResponseEntity.ok(adminService.updateCompanyManager(dto,id));
    }
    @PutMapping(UPDATECOMPANY)
    public ResponseEntity<Boolean> updateCompany(@RequestBody UpdateCompanyRequestDto dto,Long id) {
        return ResponseEntity.ok(adminService.updateCompany(dto,id));
    }
    @PutMapping(CREATECOMPANYMANAGER)
    public ResponseEntity<Boolean> createCompanyManager(@RequestBody RegisterModel model) {
        return ResponseEntity.ok(adminService.createCompanyManager(model));
    }
    @PutMapping(CREATECOMPANY)
    public ResponseEntity<Boolean> createCompany(@RequestBody RegisterModel model) {
        return ResponseEntity.ok(adminService.createCompany(model));
    }
    @PutMapping(CREATEPERSONNEL)
    public ResponseEntity<Boolean> createPersonnel(@RequestBody RegisterModel model) {
        return ResponseEntity.ok(adminService.createPersonnel(model));
    }
    @GetMapping(GETALL)
    public ResponseEntity<List<AdminSummaryResponseDto>> findAll(String tur,String token) {
        return ResponseEntity.ok(adminService.findAllSummary(tur,token));
    }

    @GetMapping(GETADMINBYID)
    public ResponseEntity<Admin> findAdminById(String token, Long id) {
        return ResponseEntity.ok(adminService.findAdminByIdWithToken(token,id));
    }
    @GetMapping(GETCOMPANYBYID)
    public ResponseEntity<Company> findCompanyById(String token, Long id) {
        return ResponseEntity.ok(adminService.findCompanyByIdWithToken(token,id));
    }
    @GetMapping(GETCOMPANYMANAGERBYID)
    public ResponseEntity<CompanyManager> findCompanyManagerById(String token, Long id) {
        return ResponseEntity.ok(adminService.findCompanyManagerByIdWithToken(token,id));
    }
    @GetMapping(GETPERSONNELBYID)
    public ResponseEntity<Personnel> findPersonnelById(String token, Long id) {
        return ResponseEntity.ok(adminService.findPersonnelByIdWithToken(token,id));
    }
}
