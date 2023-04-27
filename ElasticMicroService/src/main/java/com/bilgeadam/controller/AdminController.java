package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AddAdminRequestDto;
import com.bilgeadam.dto.request.BaseRequestDto;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.EndPoints.*;

@RestController
@RequestMapping(ELASTIC+ADMIN)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @PostMapping(SAVE)
    public ResponseEntity<Void> addAdmin(@RequestBody AddAdminRequestDto dto) {
        adminService.saveDto(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GETALL)
    public ResponseEntity<Iterable<Admin>> findAll(){
        return ResponseEntity.ok(adminService.findAll());
    }
    @PostMapping(GETALLPAGE)
    public ResponseEntity<Page<Admin>> findAll (@RequestBody BaseRequestDto dto) {
        return ResponseEntity.ok(adminService.findAll(dto));
    }
}
