package com.YouCode.ebanky.shared.dtos.responses;

import com.YouCode.ebanky.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class LoanResponseDTO {
    private Long id;

    private Double principal;
    private Double interestRate;
    private int termMonths;


    private boolean approved;

    public Long getId() {
        return id;
    }



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
