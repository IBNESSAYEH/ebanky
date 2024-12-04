package com.YouCode.ebanky.shared.dtos.requests;

import lombok.Data;

@Data
public class AccountRequestDTO {
    private Long userId;
    private Double initialBalance;
    private String accountNumber;

}
