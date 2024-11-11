package com.YouCode.ebanky.shared.dtos.responses;

import java.time.LocalDateTime;

public class InvoiceResponseDTO {
    private Long id;

    private String amountDue;
    private LocalDateTime dueDate;

    public Long getId() {
        return id;
    }



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
