package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.Transaction;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.entities.enums.Role;
import com.YouCode.ebanky.entities.enums.TransactionStatus;
import com.YouCode.ebanky.entities.enums.TransactionType;
import com.YouCode.ebanky.exceptios.GlobalExceptionHandler;
import com.YouCode.ebanky.mappers.TransactionMapper;
import com.YouCode.ebanky.repositories.AccountRepository;
import com.YouCode.ebanky.repositories.TransactionRepository;
import com.YouCode.ebanky.repositories.UserRepository;
import com.YouCode.ebanky.services.TransactionService;
import com.YouCode.ebanky.shared.dtos.requests.TransactionRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.TransactionResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {

        Transaction transaction = transactionMapper.toEntity(transactionRequestDTO);

        Account sourceAccount = accountRepository.findByAccountNumber(transactionRequestDTO.getSourceAccountNumber())
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        transaction.setSourceAccount(sourceAccount);

        Optional<Account> destinationAccountOpt = accountRepository.findByAccountNumber(transactionRequestDTO.getDestinationAccountNumber());
        if (sourceAccount.getBalance() < transaction.getAmount()) {
            throw new RuntimeException("Your balance is insufficient");
        }else if(transaction.getAmount() >= 10000){
            transaction.setStatus(TransactionStatus.PENDING);

        }else{
            transaction.setStatus(TransactionStatus.ACCEPTED);
            try{
                transactionAnalise(transaction, sourceAccount, destinationAccountOpt );

            }catch (Exception e){
                e.printStackTrace();
            }
        }


            Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toResponseDTO(savedTransaction);
    }

    @Override
    public TransactionResponseDTO AcceptTransaction(TransactionRequestDTO transactionRequestDTO) {
        Long transactionId = transactionRequestDTO.getTransactionId();
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        Long employeeId = transactionRequestDTO.getEmployeeId();
        User employee = userRepository.findById(employeeId).orElse(null);
        if (transaction == null) {
            throw new RuntimeException("Transaction not found with id: " + transactionId);
        }
        if (employee == null) {
            throw new RuntimeException("Transaction not found with id: " + transactionId);
        }

        if (employee.getRole() != Role.EMPLOYEE && transaction.getStatus() != TransactionStatus.PENDING ) {
            return transactionMapper.toResponseDTO(transaction);
        }

        Account sourceAccount = transaction.getSourceAccount();
        Optional<Account> destinationAccount = Optional.ofNullable(transaction.getDestinationAccount());
        transaction.setStatus(TransactionStatus.ACCEPTED);
        Transaction savedTransaction = null;
        try {
            transactionAnalise(transaction, sourceAccount, destinationAccount);
            savedTransaction = transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactionMapper.toResponseDTO(savedTransaction);
    }

    @Override
    public TransactionResponseDTO RejectTransaction(TransactionRequestDTO transactionRequestDTO) {

        Long transactionId = transactionRequestDTO.getTransactionId();
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        Long employeeId = transactionRequestDTO.getEmployeeId();
        User employee = userRepository.findById(employeeId).orElse(null);
        if (transaction == null) {
            throw new RuntimeException("Transaction not found with id: " + transactionId);
        }
        if (employee == null) {
            throw new RuntimeException("Transaction not found with id: " + transactionId);
        }

        if (employee.getRole() != Role.EMPLOYEE && transaction.getStatus() != TransactionStatus.PENDING ) {
            return transactionMapper.toResponseDTO(transaction);
        }
        transaction.setStatus(TransactionStatus.REFUSED);
        Transaction savedTransaction = null;
        try {
            savedTransaction = transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionMapper.toResponseDTO(savedTransaction);
    }

    @Override
    public List<TransactionResponseDTO> getTransactions() {
        return transactionRepository.findAll().stream().map(transactionMapper::toResponseDTO).collect(Collectors.toList());
    }

    public Optional<Transaction> transactionAnalise(Transaction transaction, Account sourceAccount, Optional<Account> destinationAccountOpt ) throws Exception {


            if( transaction.getType() == TransactionType.STANDARD && destinationAccountOpt.isPresent()) {
                sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());
                destinationAccountOpt.get().setBalance(destinationAccountOpt.get().getBalance() + transaction.getAmount());

            }else if( transaction.getType() == TransactionType.INSTANT && destinationAccountOpt.isPresent()){
                sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount() - 20);
                destinationAccountOpt.get().setBalance(destinationAccountOpt.get().getBalance() + transaction.getAmount());

            }else if( transaction.getType() == TransactionType.STANDING && destinationAccountOpt.isPresent()){
                sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount() - 6);
                destinationAccountOpt.get().setBalance(destinationAccountOpt.get().getBalance() + transaction.getAmount());

            }
        accountRepository.save(sourceAccount);
        destinationAccountOpt.ifPresent(accountRepository::save);
       return Optional.of(transaction);

    }
}
