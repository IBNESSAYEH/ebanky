package com.YouCode.ebanky.shared.dtos.requests;

import lombok.Data;

@Data
public class LoanRequestDTO {
    private Double principal;
    private Double interestRate;
    private int termMonths;
    private Long userId;
}
