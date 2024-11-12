package com.YouCode.ebanky.shared.dtos.responses;


import lombok.Data;
import lombok.Builder;
import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private Double monthlyIncome;
    private int creditScore;
    private Double totalSolde;

    public Double getTotalSolde() {
        return totalSolde;
    }

    public void setTotalSolde(Double totalSolde) {
        this.totalSolde = totalSolde;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}