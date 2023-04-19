package com.bilgeadam.controller;

import com.bilgeadam.dto.request.UpdateUserRequestDto;
import com.bilgeadam.dto.response.UserProfileSummaryResponseDto;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateUser(@RequestBody UpdateUserRequestDto dto) {
        return ResponseEntity.ok(userProfileService.updateUser(dto));
    }
    @GetMapping(GETALL)
    public ResponseEntity<List<UserProfileSummaryResponseDto>> findAll() {
        return ResponseEntity.ok(userProfileService.findAllSummary());
    }

    @GetMapping(GETBYID)
    public ResponseEntity<UserProfile> findById(String token, Long id) {
        return ResponseEntity.ok(userProfileService.findByIdWithToken(token, id));
    }
}
