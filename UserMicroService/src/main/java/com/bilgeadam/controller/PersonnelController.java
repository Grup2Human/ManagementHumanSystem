package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateLeaveRequestDto;
import com.bilgeadam.dto.request.UpdateCompanyManagerRequestDto;
import com.bilgeadam.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
