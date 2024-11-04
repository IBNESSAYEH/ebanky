package com.YouCode.ebanky.requests;

import com.YouCode.ebanky.entities.enums.AccountStatus;
import com.YouCode.ebanky.shared.dto.UserDto;

import java.time.LocalDateTime;

public class AccountRequest {
    private String account_number;
    private Double balance;
    private LocalDateTime created_at;
    private AccountStatus status;
    private UserDto user;
}
