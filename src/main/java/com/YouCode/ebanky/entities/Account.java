package com.YouCode.ebanky.entities;

import com.YouCode.ebanky.entities.enums.AccountStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "accounts")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountNumber;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private LocalDateTime created_at;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

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



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccount_number() {
        return accountNumber;
    }

    public void setAccount_number(String account_number) {
        this.accountNumber = account_number;
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

