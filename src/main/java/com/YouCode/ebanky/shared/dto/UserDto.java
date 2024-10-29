package com.YouCode.ebanky.shared.dto;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.Invoice;
import com.YouCode.ebanky.entities.Loan;
import com.YouCode.ebanky.entities.enums.Role;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Data
public class UserDto implements Serializable {


    @Serial
    private static final long serialVersionUID = 8820176629895539197L;
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;

    // the wrapper Boolean not the primitive type boolean
    private Boolean emailVerificationStatus = false;
    private int age;
    private Double monthlyIncome;
    private int creditScore;
    private Role role;
    private List<Account> accounts;
    private List<Invoice> invoices;
    private List<Loan> loans;


}
