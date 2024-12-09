package com.YouCode.ebanky.services;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.shared.dtos.requests.AccountRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.AccountResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface AccountService {
    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
    List<AccountResponseDTO> getAllAccounts();
    void deleteAccount(Long id);
    AccountResponseDTO blockAccount(Long id,AccountRequestDTO accountRequestDTO);
    AccountResponseDTO activeAccount(Long id,AccountRequestDTO accountRequestDTO);
    AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO, String email);
    AccountResponseDTO getAccountById(Long id, String email);
    Account findByAccountNumber(String accountNumber);
}
