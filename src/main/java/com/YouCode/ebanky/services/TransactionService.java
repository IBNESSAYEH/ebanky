package com.YouCode.ebanky.services;

import com.YouCode.ebanky.shared.dtos.requests.TransactionRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.TransactionResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO);
    TransactionResponseDTO AcceptTransaction(TransactionRequestDTO transactionRequestDTO);
    TransactionResponseDTO RejectTransaction(TransactionRequestDTO transactionRequestDTO);
    List<TransactionResponseDTO> getTransactions();
}
