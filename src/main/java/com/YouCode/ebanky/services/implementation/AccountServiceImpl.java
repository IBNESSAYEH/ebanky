package com.YouCode.ebanky.services.implementation;


import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.mappers.AccountMapper;
import com.YouCode.ebanky.repositories.AccountRepository;
import com.YouCode.ebanky.repositories.UserRepository;
import com.YouCode.ebanky.services.AccountService;
import com.YouCode.ebanky.shared.dtos.requests.AccountRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.AccountResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountMapper accountMapper;

    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        Account account = accountMapper.toEntity(accountRequestDTO);
        account.setUser(userRepository.findById(accountRequestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        account.setBalance(accountRequestDTO.getInitialBalance());
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toResponseDTO(savedAccount);
    }

    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::toResponseDTO).collect(Collectors.toList());
    }

    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return accountMapper.toResponseDTO(account);
    }

    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(accountRequestDTO.getInitialBalance());
        account.setAccount_number(accountRequestDTO.getAccountNumber());
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toResponseDTO(updatedAccount);
    }

    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }
}