package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.services.TransactionService;
import com.YouCode.ebanky.services.UserService;
import com.YouCode.ebanky.shared.dtos.requests.TransactionRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.TransactionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public TransactionResponseDTO createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionService.createTransaction(transactionRequestDTO);
    }

    @PatchMapping("/accept")
    public TransactionResponseDTO AcceptTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionService.AcceptTransaction(transactionRequestDTO);
    }

    @PatchMapping("/reject")
    public TransactionResponseDTO RejectTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionService.RejectTransaction(transactionRequestDTO);
    }

    @GetMapping
    public List<TransactionResponseDTO> getTransactions() {
        return transactionService.getTransactions();
    }
}
