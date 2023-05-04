package com.bilgeadam.controller;


import com.bilgeadam.dto.request.ChangeStatusRequestDto;
import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.AuthRegisterResponseDto;
import com.bilgeadam.exception.AuthServiceException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.repository.entity.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.constants.EndPoints.*;
import com.bilgeadam.service.AuthService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<AuthRegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto,String adminPassword) {
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_PASSWORD_UNMATCH);
        return ResponseEntity.ok(authService.register(dto,adminPassword));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDto dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }
    @PostMapping(CHANGESTATUS)
    public ResponseEntity<Boolean> activateStatus (@RequestBody ChangeStatusRequestDto dto) {
        return ResponseEntity.ok(authService.changeStatus(dto));
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Auth>> findAll(String token) {
        return ResponseEntity.ok(authService.findAll(token));
    }

    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok("Genell birr mesajjj git pushladik yine");
    }

    @PutMapping(FORGETPASSWORD)
    public ResponseEntity<Boolean> forgetPassword(String email) {
        return ResponseEntity.ok(authService.changePassword(email));
    }

}
