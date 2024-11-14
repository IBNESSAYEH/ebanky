package com.YouCode.ebanky.shared.dtos.requests;

import com.YouCode.ebanky.entities.enums.TransactionStatus;
import com.YouCode.ebanky.entities.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TransactionRequestDTO {

    private Long employeeId;
    private Long transactionId;


    @NotNull
    @NotBlank(message = "Typee cannot be blank")
    private String type;

    @NotNull
    @NotBlank(message = "ammount cannot be blank")
    private Double amount;

    @NotNull
    @NotBlank(message = "Status cannot be blank")
    private String status;
    @NotNull
    @NotBlank(message = "sourceAccountNumber cannot be blank")
    private String sourceAccountNumber;

    @NotNull
    @NotBlank(message = "destinationAccountNumber cannot be blank")
    private String destinationAccountNumber;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }
}
