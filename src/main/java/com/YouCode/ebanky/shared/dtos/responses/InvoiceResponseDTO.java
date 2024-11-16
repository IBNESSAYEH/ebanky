package com.YouCode.ebanky.shared.dtos.responses;

import lombok.Data;

@Data
public class InvoiceResponseDTO {
    private Long id;
    private String amountDue;
    private String dueDate;
    private Long accountId;
}
