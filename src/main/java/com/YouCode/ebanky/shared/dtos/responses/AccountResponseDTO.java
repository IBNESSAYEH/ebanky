package com.YouCode.ebanky.shared.dtos.responses;


import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;
import com.YouCode.ebanky.entities.enums.AccountStatus;

public class AccountResponseDTO {
    private Long id;
    private String accountNumber;
    private Double balance;
    private String status;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}