package com.bilgeadam.controller;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.CompanyManagerSummaryResponseDto;
import com.bilgeadam.dto.response.LeaveDemandsResponseDto;
import com.bilgeadam.dto.response.PersonnelSummaryResponseDto;
import com.bilgeadam.repository.entity.Advance;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.entity.Personnel;
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
    public ResponseEntity<Boolean> updateCompanyManager(@RequestBody UpdateCompanyManagerRequestDto dto,Long id) {
        return ResponseEntity.ok(companyManagerService.updateCompanyManager(dto,id));
    }
    @PutMapping(UPDATEPERSONNEL)
    public ResponseEntity<Boolean> updatePersonnel(@RequestBody UpdatePersonnelRequestDto dto,Long id) {
        return ResponseEntity.ok(companyManagerService.updatePersonnel(dto,id));
    }
    @PutMapping(UPDATECOMPANY)
    public ResponseEntity<Boolean> updateCompany(@RequestBody UpdateCompanyRequestDto dto, Long id) {
        return ResponseEntity.ok(companyManagerService.updateCompany(dto,id));
    }
//    @GetMapping(GETALL)
//    public ResponseEntity<List<CompanyManagerSummaryResponseDto>> findAll() {
//        return ResponseEntity.ok(companyManagerService.findAllSummary());
//    }

    @GetMapping(GETCOMPANYMANAGERBYID)
    public ResponseEntity<CompanyManager> findById(String token, Long id) {
        return ResponseEntity.ok(companyManagerService.findCompanyManagerByIdWithToken(token,id));
    }
    @GetMapping(GETPERSONNELBYID)
    public ResponseEntity<Personnel> findPersonnelById(String token, Long id) {
        return ResponseEntity.ok(companyManagerService.findPersonnelByIdWithToken(token,id));
    }

    @GetMapping(GETALLCOMPANYMANAGER)
    public ResponseEntity<List<CompanyManagerSummaryResponseDto>> findAllCompanyManager(String token) {
        return ResponseEntity.ok(companyManagerService.findAllSummaryCompanyManager(token));
    }
    @GetMapping(GETALLPERSONNEL)
    public ResponseEntity<List<PersonnelSummaryResponseDto>> findAllPersonnel(String token) {
        return ResponseEntity.ok(companyManagerService.findAllSummaryPersonnel(token));
    }

    @PutMapping(CREATECOMPANYMANAGER)
    public ResponseEntity<Boolean> createCompanyManager(@RequestBody CompanyManagerSaveRequestDto dto) {
        return ResponseEntity.ok(companyManagerService.createCompanyManager(dto));
    }

    @PutMapping(CREATEPERSONNEL)
    public ResponseEntity<Boolean> createPersonnel(@RequestBody PersonnelSaveRequestDto dto) {
        return ResponseEntity.ok(companyManagerService.createPersonnel(dto));
    }
    @GetMapping(GETALLPERSONNELLEAVEREQUESTS)
    public ResponseEntity<List<LeaveDemandsResponseDto>> findAllLeaveRequests (String token) {
        return ResponseEntity.ok(companyManagerService.findAllLeaveRequests(token));
    }
    @PutMapping(PERSONNELLEAVEAPPROVAL)
    public ResponseEntity<Boolean> leaveApproval(String token,Long leaveId,String onay) {
        return ResponseEntity.ok(companyManagerService.leaveApproval(token,leaveId,onay));
    }
    @GetMapping(GETALLPERSONNELADVANCEREQUESTS)
    public ResponseEntity<List<Advance>> findAllAdvanceRequests (String token) {
        return ResponseEntity.ok(companyManagerService.findAllAdvanceRequests(token));
    }
    @PutMapping(PERSONNELADVANCEAPPROVAL)
    public ResponseEntity<Boolean> advanceApproval(String token,Long advanceId,String onay) {
        return ResponseEntity.ok(companyManagerService.advanceApproval(token,advanceId,onay));
    }
    @GetMapping(GETALLPERSONNELEXPENSEREQUESTS)
    public ResponseEntity<List<Expense>> findAllExpenseRequests (String token) {
        return ResponseEntity.ok(companyManagerService.findAllExpenseRequests(token));
    }
    @PutMapping(PERSONNELEXPENSEAPPROVAL)
    public ResponseEntity<Boolean> expenseApproval(String token,Long expenseId,String onay) {
        return ResponseEntity.ok(companyManagerService.expenseApproval(token,expenseId,onay));
    }
}
