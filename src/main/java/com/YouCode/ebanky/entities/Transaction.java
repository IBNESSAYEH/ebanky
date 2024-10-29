package com.YouCode.ebanky.entities;

import com.YouCode.ebanky.entities.enums.TransactionStatus;
import com.YouCode.ebanky.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private boolean executed;

    public void execute() {
        if (!executed && status == TransactionStatus.ACCEPTED) {
            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);
            this.executed = true;
        }
    }

    public void cancel() {
        if (!executed) {
            this.status = TransactionStatus.REFUSED;
        } else {
            throw new IllegalStateException("Cannot cancel executed transaction");
        }
    }
}
