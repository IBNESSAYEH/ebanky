package com.YouCode.ebanky.entities;

import com.YouCode.ebanky.entities.enums.AccountStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "accounts")

public class Account {
    @Id
    private Long id;

    @Column(unique = true)
    private String account_number;

    private Double balance;

    private LocalDateTime created_at;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

//    @OneToMany(mappedBy = "sourceAccount")
//    private List<Transaction> outgoingTransactions;
//
//    @OneToMany(mappedBy = "destinationAccount")
//    private List<Transaction> incomingTransactions;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}

