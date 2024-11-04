package com.YouCode.ebanky.entities;

import com.YouCode.ebanky.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -8495478474052046335L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 70, unique = true)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column(nullable = true)
    private String emailVerificationToken;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean emailVerificationStatus = false;

    @Column(nullable = false)
    private int age;

    @Column(nullable = true)
    private Double monthlyIncome;

    @Column(nullable = true)
    private int creditScore;

//    @Enumerated(EnumType.STRING)
//    private Role role;
//
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

//    @OneToMany(mappedBy = "user")
//    private List<Invoice> invoices;
//
//    @OneToMany(mappedBy = "user")
//    private List<Loan> loans;

    public void register() {
        return;
    }

    public boolean login() {

        return false;
    }
}