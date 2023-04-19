package com.bilgeadam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/authmicroservice")
    public ResponseEntity<String> fallbackAuth() {
       return ResponseEntity.ok("Auth servisi şu anda hizmet verememektedir. Lütfen daha sonra deneyiniz.");
    }
    @GetMapping("/usermicroservice")
    public ResponseEntity<String> fallbackUser() {
        return ResponseEntity.ok("User servisi şu anda hizmet verememektedir. Lütfen daha sonra deneyiniz.");
    }
}
