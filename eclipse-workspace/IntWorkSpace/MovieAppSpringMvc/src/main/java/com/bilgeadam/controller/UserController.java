package com.bilgeadam.controller;


import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.UserResgisterRequestDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/*
 login metodu yazacağız

 Dışardan gerekli bilgileri alacagız
 bilgiler dogru ise=>
 ve bazı bilgileri eleyip geri kalanları donecegiz
bilgiler yanlış ise=> runtime exception fırlatalım

 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /*
        @RequestParam()=> default olarak parametreyi zorunlu hale getiriri ve
        o parametreye özellikler kazandırır

     */

    @GetMapping("/save")
    public ResponseEntity<User> createUser(String name, String surName, @RequestParam Optional<String> password, @RequestParam(defaultValue ="example@gmail.com") String email){
            String mypassword=null;
        if (password.isPresent()){
                mypassword=password.get();
            }
        return ResponseEntity.ok(userService.createUser(name,surName,mypassword,email));
    }
    @GetMapping("/register")
    public ResponseEntity<User> register(UserResgisterRequestDto dto){
        return ResponseEntity.ok(userService.register2(dto));
    }

    @PostMapping("/register2")
    public ResponseEntity<User> register2(@RequestBody UserResgisterRequestDto dto){
        return ResponseEntity.ok(userService.register2(dto));
    }

    @GetMapping("/findallbyordername")
    public ResponseEntity <List<User>> findAllByOrderByName(){
        return ResponseEntity.ok(userService.findAllByOrderByName());
    }

    @GetMapping("/containsname")
    public   ResponseEntity <List<User>> findByNameContainingIgnoreCase(String value){
        return ResponseEntity.ok(userService.findByNameContainingIgnoreCase(value));
    }
    @GetMapping("/containsemail")
    public   ResponseEntity <List<User>> findByEmailContainingIgnoreCase(String value){
        return ResponseEntity.ok(userService.findByEmailContainingIgnoreCase(value));
    }
    @GetMapping("/endingemail")
    public   ResponseEntity <List<User>> findByEmailEndingWith(String value){
        return ResponseEntity.ok(userService.findByEmailEndingWith(value));
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){

            return ResponseEntity.ok(userService.login(dto));
    }
    @PostMapping("/login2")
    public ResponseEntity<?> login2(@RequestBody LoginRequestDto dto){
        try {
            return ResponseEntity.ok(userService.login(dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/passwordcontrol")
    public    ResponseEntity <List<User>>passwordLongerThan(int value){
        return  ResponseEntity.ok( userService.passwordLongerThan(value));
    }
    @GetMapping("/passwordcontrol2")
    public    ResponseEntity <List<User>>passwordLongerThan2(int value){
        return  ResponseEntity.ok( userService.passwordLongerThan2(value));
    }
    @GetMapping("/passwordcontrol3")
    public    ResponseEntity <List<User>> passwordLongerThan3(int value){
        return  ResponseEntity.ok( userService.passwordLongerThan3(value));
    }



}
