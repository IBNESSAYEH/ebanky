package com.YouCode.ebanky.controllers;


import com.YouCode.ebanky.services.AccountService;
import com.YouCode.ebanky.shared.dtos.requests.AccountRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.AccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public AccountResponseDTO createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.createAccount(accountRequestDTO);
    }

    @GetMapping
    public List<AccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/{id}")
    public AccountResponseDTO updateAccount(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.updateAccount(id, accountRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @PatchMapping("/{id}/block")
    public AccountResponseDTO blockAccount(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.blockAccount(id, accountRequestDTO);
    }
    @PatchMapping("/{id}/activate")
    public AccountResponseDTO activeAccount(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.blockAccount(id, accountRequestDTO);
    }
}
