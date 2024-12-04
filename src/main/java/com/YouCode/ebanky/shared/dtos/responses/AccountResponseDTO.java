package com.YouCode.ebanky.shared.dtos.responses;


import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;
import com.YouCode.ebanky.entities.enums.AccountStatus;
@Data
public class AccountResponseDTO {

    private String accountNumber;
    private Double balance;
    private String status;
    private Long userId;

}