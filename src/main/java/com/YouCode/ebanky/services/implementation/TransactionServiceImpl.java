package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.Transaction;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.entities.enums.TransactionStatus;
import com.YouCode.ebanky.repositories.AccountRepository;
import com.YouCode.ebanky.repositories.TransactionRepository;
import com.YouCode.ebanky.repositories.UserRepository;
import com.YouCode.ebanky.services.TransactionService;
import com.YouCode.ebanky.shared.dtos.requests.TransactionRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.TransactionResponseDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private AccountRepository accountRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = modelMapper.map(transactionRequestDTO, Transaction.class);
        Account sourceAccount = accountRepository.findByAccountNumber(transactionRequestDTO.getSourceAccountNumber())
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        transaction.setSourceAccount(sourceAccount);

        Optional<Account> destinationAccountOpt = accountRepository.findByAccountNumber(transactionRequestDTO.getDestinationAccountNumber());

        if (sourceAccount.getBalance() < transaction.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        if (transaction.getAmount() >= 10000) {
            transaction.setStatus(TransactionStatus.PENDING);
        } else {
            transaction.setStatus(TransactionStatus.ACCEPTED);
            transactionAnalise(transaction, sourceAccount, destinationAccountOpt);
        }

        Transaction savedTransaction = transactionRepository.save(transaction);
        return modelMapper.map(savedTransaction, TransactionResponseDTO.class);
    }

    @Override
    @Transactional
    public TransactionResponseDTO AcceptTransaction(TransactionRequestDTO transactionRequestDTO) {
        Long transactionId = transactionRequestDTO.getTransactionId();
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        Long employeeId = transactionRequestDTO.getEmployeeId();
        User employee = userRepository.findById(employeeId).orElse(null);

        if (transaction == null) {
            throw new RuntimeException("Transaction not found with id: " + transactionId);
        }

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        UserDetails authenticatedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authenticatedUser != null) {
            String userRole = authenticatedUser.getAuthorities().stream()
                    .findFirst()
                    .map(authority -> authority.getAuthority())
                    .orElse("");

            if (!userRole.equals("ROLE_ADMIN") && !userRole.equals("ROLE_EMPLOYEE")) {
                throw new SecurityException("You are not authorized to approve this transaction");
            }
        }

        if (transaction.getStatus() != TransactionStatus.PENDING) {
            return modelMapper.map(transaction, TransactionResponseDTO.class);
        }

        Account sourceAccount = transaction.getSourceAccount();
        Optional<Account> destinationAccountOpt = Optional.ofNullable(transaction.getDestinationAccount());
        transaction.setStatus(TransactionStatus.ACCEPTED);

        try {
            transactionAnalise(transaction, sourceAccount, destinationAccountOpt);
            transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelMapper.map(transaction, TransactionResponseDTO.class);
    }

    @Override
    @Transactional
    public TransactionResponseDTO RejectTransaction(TransactionRequestDTO transactionRequestDTO) {
        Long transactionId = transactionRequestDTO.getTransactionId();
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        Long employeeId = transactionRequestDTO.getEmployeeId();
        User employee = userRepository.findById(employeeId).orElse(null);

        if (transaction == null) {
            throw new RuntimeException("Transaction not found with id: " + transactionId);
        }

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        UserDetails authenticatedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authenticatedUser != null) {
            String userRole = authenticatedUser.getAuthorities().stream()
                    .findFirst()
                    .map(authority -> authority.getAuthority())
                    .orElse("");

            if (!userRole.equals("ROLE_ADMIN") && !userRole.equals("ROLE_EMPLOYEE")) {
                throw new SecurityException("You are not authorized to reject this transaction");
            }
        }

        if (transaction.getStatus() != TransactionStatus.PENDING) {
            return modelMapper.map(transaction, TransactionResponseDTO.class);
        }

        transaction.setStatus(TransactionStatus.REFUSED);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return modelMapper.map(savedTransaction, TransactionResponseDTO.class);
    }

    @Override
    public List<TransactionResponseDTO> getTransactions() {
        return transactionRepository.findAll().stream()
                .map(transaction -> modelMapper.map(transaction, TransactionResponseDTO.class))
                .collect(Collectors.toList());
    }

    private void transactionAnalise(Transaction transaction, Account sourceAccount, Optional<Account> destinationAccountOpt) {

        if (destinationAccountOpt.isPresent()) {
            Account destinationAccount = destinationAccountOpt.get();
            sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());
            destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getAmount());
            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);
        }
    }
}
