package com.YouCode.ebanky.services;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.shared.dtos.requests.AccountRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.AccountResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface AccountService {
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);

    public List<AccountResponseDTO> getAllAccounts();

    public AccountResponseDTO getAccountById(Long id);

    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO);

    public void deleteAccount(Long id);

    AccountResponseDTO blockAccount(Long id,AccountRequestDTO accountRequestDTO);
    AccountResponseDTO activeAccount(Long id,AccountRequestDTO accountRequestDTO);

    Account findByAccountNumber(String accountNumber);
}
