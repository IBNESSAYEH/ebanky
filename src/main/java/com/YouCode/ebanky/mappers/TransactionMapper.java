package com.YouCode.ebanky.mappers;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.Transaction;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.services.AccountService;
import com.YouCode.ebanky.shared.dtos.requests.TransactionRequestDTO;
import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.TransactionResponseDTO;
import com.YouCode.ebanky.shared.dtos.responses.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {AccountService.class})
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "sourceAccount", source = "sourceAccountNumber")
    @Mapping(target = "destinationAccount", source = "destinationAccountNumber")
    Transaction toEntity(TransactionRequestDTO transactionRequestDTO);

    TransactionResponseDTO toResponseDTO(Transaction transaction);

    default Account mapAccount(String accountNumber, AccountService accountService) {
        if (accountNumber == null) {
            return null;
        }
        return accountService.findByAccountNumber(accountNumber);
    }
}
