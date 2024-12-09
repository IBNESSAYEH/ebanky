package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.services.TransactionService;
import com.YouCode.ebanky.shared.dtos.requests.TransactionRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.TransactionResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {


    private TransactionService transactionService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PostMapping
    public TransactionResponseDTO createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionService.createTransaction(transactionRequestDTO);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PatchMapping("/accept")
    public TransactionResponseDTO AcceptTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionService.AcceptTransaction(transactionRequestDTO);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PatchMapping("/reject")
    public TransactionResponseDTO RejectTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionService.RejectTransaction(transactionRequestDTO);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @GetMapping
    public List<TransactionResponseDTO> getTransactions() {
        return transactionService.getTransactions();
    }
}
