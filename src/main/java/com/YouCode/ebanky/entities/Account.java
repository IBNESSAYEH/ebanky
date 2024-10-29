package com.YouCode.ebanky.entities;

import com.YouCode.ebanky.entities.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    private Long id;

    @Column(unique = true)
    private String account_number;

    private Double balance;

    private LocalDateTime created_at;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "sourceAccount")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "destinationAccount")
    private List<Transaction> incomingTransactions;

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

