package com.bilgeadam.controller;

import com.bilgeadam.dto.request.UpdateUserRequestDto;
import com.bilgeadam.dto.response.AdminSummaryResponseDto;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateUser(@RequestBody UpdateUserRequestDto dto) {
        return ResponseEntity.ok(adminService.updateUser(dto));
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
