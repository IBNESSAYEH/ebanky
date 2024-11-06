package com.YouCode.ebanky.requests;

//import com.YouCode.ebanky.entities.Account;
//import com.YouCode.ebanky.entities.Invoice;
//import com.YouCode.ebanky.entities.Loan;
import com.YouCode.ebanky.entities.enums.Role;


import java.util.List;


public class UserRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    private int age;
    private Double monthlyIncome;
    private int creditScore;
    private Role role;
    private List<AccountRequest> accounts;
//    private List<Invoice> invoices;
//    private List<Loan> loans;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<AccountRequest> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountRequest> accounts) {
        this.accounts = accounts;
    }
}
