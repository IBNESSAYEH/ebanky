package com.YouCode.ebanky.shared.dtos.requests;

import lombok.Data;

@Data
public class InvoiceRequestDTO {
    private String amountDue;
    private Long accountId;
    private String dueDate;
}
