package com.YouCode.ebanky.shared.dtos.responses;

import com.YouCode.ebanky.entities.enums.TransactionStatus;
import com.YouCode.ebanky.entities.enums.TransactionType;
import lombok.Data;

@Data
public class TransactionResponseDTO {
    private Long id;
    private TransactionType type;
    private Double amount;
    private TransactionStatus status;
    private boolean executed;


}
