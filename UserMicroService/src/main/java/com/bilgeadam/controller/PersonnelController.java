package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateLeaveRequestDto;
import com.bilgeadam.dto.request.UpdateCompanyManagerRequestDto;
import com.bilgeadam.dto.response.DemandsResponseDto;
import com.bilgeadam.dto.response.PersonnelSummaryResponseDto;
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
//    @PostMapping(FINDALLLEAVEREQUEST)
//    public ResponseEntity<List<DemandsResponseDto>> findAllLeaveRequests(String token) {
//        return ResponseEntity.ok(personnelService.findAllLeaveRequests(token));
//    }

    @PostMapping(FINDALLLEAVEREQUEST)
    public ResponseEntity<List<Leave>> findAllLeaveRequests(String token) {
        return ResponseEntity.ok(personnelService.findAllLeaveRequests2(token));
    }
}
