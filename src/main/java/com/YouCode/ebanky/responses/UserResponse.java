package com.YouCode.ebanky.responses;

//import com.YouCode.ebanky.entities.Account;
//import com.YouCode.ebanky.entities.Invoice;
//import com.YouCode.ebanky.entities.Loan;
import com.YouCode.ebanky.entities.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private Double monthlyIncome;
    private int creditScore;
    private Role role;
//    private List<Account> accounts;
//    private List<Invoice> invoices;
//    private List<Loan> loans;

}
