package com.YouCode.ebanky.shared.dto;


import com.YouCode.ebanky.entities.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountDto {

    private Long id;
    private String account_number;
    private Double balance;
    private LocalDateTime created_at;
    private AccountStatus status;
    private UserDto user;
}
