package com.YouCode.ebanky.entities;

import com.YouCode.ebanky.entities.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class AccountEntity {
    @Id
    private UUID id;

    @Column(unique = true)
    private String account_number;

    private Double balance;

    private LocalDateTime created_at;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "sourceAccount")
    private List<TransactionEntity> outgoingTransactions;

    @OneToMany(mappedBy = "destinationAccount")
    private List<TransactionEntity> incomingTransactions;

    public void deposit(Double amount) {
        this.balance += amount;
    }

    public void withdraw(Double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
        } else {
            throw new IllegalStateException("Insufficient funds");
        }
    }

    
}

