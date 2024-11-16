package com.YouCode.ebanky.shared.dtos.responses;

import lombok.Data;

@Data
public class LoanResponseDTO {
    private Long id;
    private Double principal;
    private Double interestRate;
    private int termMonths;
    private boolean approved;
}
