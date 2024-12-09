package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.services.AccountService;
import com.YouCode.ebanky.shared.dtos.requests.AccountRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.AccountResponseDTO;
import com.YouCode.ebanky.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final JwtService jwtService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO,
                                                            @RequestHeader("Authorization") String token) {
        String email = jwtService.extractUsername(token);
        accountRequestDTO.setUserId(jwtService.getUserIdFromEmail(email));
        AccountResponseDTO responseDTO = accountService.createAccount(accountRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id,
                                                             @RequestHeader("Authorization") String token) {
        String email = jwtService.extractUsername(token);
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(id, email);
        return ResponseEntity.ok(accountResponseDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id,
                                                            @RequestBody AccountRequestDTO accountRequestDTO,
                                                            @RequestHeader("Authorization") String token) {
        String email = jwtService.extractUsername(token);
        AccountResponseDTO updatedAccount = accountService.updateAccount(id, accountRequestDTO, email);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/block")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDTO> blockAccount(@PathVariable Long id,
                                                           @RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO blockedAccount = accountService.blockAccount(id, accountRequestDTO);
        return ResponseEntity.ok(blockedAccount);
    }

    @PatchMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDTO> activateAccount(@PathVariable Long id,
                                                              @RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO activatedAccount = accountService.activeAccount(id, accountRequestDTO);
        return ResponseEntity.ok(activatedAccount);
    }
}
