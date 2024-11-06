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

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
