package com.YouCode.ebanky.shared.dtos.requests;

import java.time.LocalDateTime;

public class InvoiceRequestDTO {


    private String amountDue;
    private LocalDateTime dueDate;

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
