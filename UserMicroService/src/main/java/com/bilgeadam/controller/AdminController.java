package com.bilgeadam.controller;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.Admin;
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
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateAdmin(@RequestBody UpdateAdminRequestDto dto) {
        return ResponseEntity.ok(adminService.updateAdmin(dto));
    }
    @PutMapping(UPDATEPERSONNEL)
    public ResponseEntity<Boolean> updatePersonnel(@RequestBody UpdatePersonnelRequestDto dto) {
        return ResponseEntity.ok(adminService.updatePersonnel(dto));
    }
    @PutMapping(UPDATECOMPANYMANAGER)
    public ResponseEntity<Boolean> updateCompanyManager(@RequestBody UpdateCompanyManagerRequestDto dto) {
        return ResponseEntity.ok(adminService.updateCompanyManager(dto));
    }
    @PutMapping(UPDATECOMPANY)
    public ResponseEntity<Boolean> updateCompany(@RequestBody UpdateCompanyRequestDto dto) {
        return ResponseEntity.ok(adminService.updateCompany(dto));
    }
    @PutMapping(CREATECOMPANYMANAGER)
    public ResponseEntity<Boolean> updateCompany(@RequestBody RegisterModel model) {
        return ResponseEntity.ok(adminService.createCompanyManager(model));
    }
    @GetMapping(GETALL)
    public ResponseEntity<List<AdminSummaryResponseDto>> findAll() {
        return ResponseEntity.ok(adminService.findAllSummary());
    }

    @GetMapping(GETBYID)
    public ResponseEntity<Admin> findById(String token, Long id) {
        return ResponseEntity.ok(adminService.findByIdWithToken(token,id));
    }
}
