package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.enums.AccountStatus;
import com.YouCode.ebanky.repositories.AccountRepository;
import com.YouCode.ebanky.repositories.UserRepository;
import com.YouCode.ebanky.services.AccountService;
import com.YouCode.ebanky.shared.dtos.requests.AccountRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.AccountResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {

        Account account = modelMapper.map(accountRequestDTO, Account.class);

        account.setUser(userRepository.findById(accountRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        account.setBalance(accountRequestDTO.getInitialBalance());
        account.setCreatedAt(LocalDateTime.now());
        account.setStatus(AccountStatus.ACTIVE);

        Account savedAccount = accountRepository.save(account);
        return modelMapper.map(savedAccount, AccountResponseDTO.class);
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {

        return accountRepository.findAll()
                .stream()
                .map(account -> modelMapper.map(account, AccountResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return modelMapper.map(account, AccountResponseDTO.class);
    }

    @Override
    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(accountRequestDTO.getInitialBalance());
        account.setAccountNumber(accountRequestDTO.getAccountNumber());

        Account updatedAccount = accountRepository.save(account);
        return modelMapper.map(updatedAccount, AccountResponseDTO.class);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }

    @Override
    public AccountResponseDTO blockAccount(Long id, AccountRequestDTO accountRequestDTO) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setStatus(AccountStatus.BLOCKED);

        Account updatedAccount = accountRepository.save(account);
        return modelMapper.map(updatedAccount, AccountResponseDTO.class);
    }

    @Override
    public AccountResponseDTO activeAccount(Long id, AccountRequestDTO accountRequestDTO) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setStatus(AccountStatus.ACTIVE);

        Account updatedAccount = accountRepository.save(account);
        return modelMapper.map(updatedAccount, AccountResponseDTO.class);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {

        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
