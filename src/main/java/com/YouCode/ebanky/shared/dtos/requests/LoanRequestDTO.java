package com.YouCode.ebanky.shared.dtos.requests;

import com.YouCode.ebanky.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class LoanRequestDTO {

    private Double principal;
    private Double interestRate;
    private int termMonths;



    private boolean approved;

    public Double getPrincipal() {
        return principal;
    }

    public void setPrincipal(Double principal) {
        this.principal = principal;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
