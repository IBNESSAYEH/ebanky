package com.YouCode.ebanky.controllers;


import com.YouCode.ebanky.auth.RegisterRequest;
import com.YouCode.ebanky.services.implementation.KeycloakAuthService;
import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.LoginDto;
import com.YouCode.ebanky.shared.dtos.responses.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authKeyCloak")
@RequiredArgsConstructor
public class AuthKeyCloakController {

    private final KeycloakAuthService keycloakAuthService;


    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequest registerRequest) {
        try {
            return ResponseEntity.ok(keycloakAuthService.register(registerRequest));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<LoginDto> authenticate(@RequestBody UserRequestDTO loginRequest) {
        try {
            return ResponseEntity.ok(keycloakAuthService.authenticate(loginRequest));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginDto("Authentication failed", 0, null));
        }
    }
}