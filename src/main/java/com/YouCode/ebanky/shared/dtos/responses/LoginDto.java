package com.YouCode.ebanky.shared.dtos.responses;


import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginDto {
    private String token;
    private long expiresIn;
    private UserRequestDTO user;
}