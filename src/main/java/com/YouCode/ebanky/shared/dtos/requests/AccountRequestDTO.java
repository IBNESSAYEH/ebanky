package com.YouCode.ebanky.shared.dtos.requests;

import lombok.Data;
import lombok.Builder;

public class AccountRequestDTO {
    private Long userId;
    private Double initialBalance;
    private String accountNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
