package com.YouCode.ebanky.shared.dtos.responses;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.enums.TransactionStatus;
import com.YouCode.ebanky.entities.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class TransactionResponseDTO {
    private Long id;

   
    private TransactionType type;
    private Double amount;

    private TransactionStatus status;

    private boolean executed;

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }
}
