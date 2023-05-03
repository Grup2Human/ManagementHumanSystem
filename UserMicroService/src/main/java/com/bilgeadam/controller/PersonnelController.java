package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateAdvanceRequestDto;
import com.bilgeadam.dto.request.CreateExpenseRequestDto;
import com.bilgeadam.dto.request.CreateLeaveRequestDto;
import com.bilgeadam.repository.entity.Advance;
import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.entity.Leave;
import com.bilgeadam.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bilgeadam.constants.EndPoints.*;

@RestController
@RequestMapping(PERSONNEL)
@RequiredArgsConstructor
public class PersonnelController {
    private final PersonnelService personnelService;

    @PostMapping(CREATELEAVEREQUEST)
    public ResponseEntity<Boolean> createLeaveRequest(@RequestBody CreateLeaveRequestDto dto, String token) {
        return ResponseEntity.ok(personnelService.createLeaveRequest(dto, token));
    }

    @PostMapping(FINDALLLEAVEREQUEST)
    public ResponseEntity<List<Leave>> findAllLeaveRequests(String token) {
        return ResponseEntity.ok(personnelService.findAllLeaveRequests(token));
    }
    @PostMapping(CREATEADVANCEREQUEST)
    public ResponseEntity<Boolean> createAdvanceRequest(@RequestBody CreateAdvanceRequestDto dto, String token) {
        return ResponseEntity.ok(personnelService.createAdvanceRequest(dto, token));
    }
    @PostMapping(FINDALLADVANCEREQUEST)
    public ResponseEntity<List<Advance>> findAllAdvanceRequests(String token) {
        return ResponseEntity.ok(personnelService.findAllAdvanceRequests(token));
    }
    @PostMapping(CREATEEXPENSEREQUEST)
    public ResponseEntity<Boolean> createExpenseRequest(@RequestBody CreateExpenseRequestDto dto, String token) {
        return ResponseEntity.ok(personnelService.createExpenseRequest(dto, token));
    }
    @PostMapping(FINDALLEXPENSEREQUEST)
    public ResponseEntity<List<Expense>> findAllExpenseRequests(String token) {
        return ResponseEntity.ok(personnelService.findAllExpenseRequests(token));
    }
//    @PostMapping(CREATEADVANCEREQUEST)
//    public ResponseEntity<Boolean> createExpenseRequest(@RequestBody CreateExpenseRequestDto dto, String token) {
//        return ResponseEntity.ok(personnelService.createExpenseRequest(dto, token));
//    }
}
