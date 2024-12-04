package com.YouCode.ebanky.shared.dtos.requests;

import com.YouCode.ebanky.entities.enums.TransactionStatus;
import com.YouCode.ebanky.entities.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
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

}
