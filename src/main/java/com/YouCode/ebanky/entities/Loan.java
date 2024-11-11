package com.YouCode.ebanky.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "loans")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double principal;
    private Double interestRate;
    private int termMonths;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean approved;

    public boolean applyForLoan() {
        return false;
    }

    public Double calculateMonthlyPayment() {
        return 0.0;
    }
}