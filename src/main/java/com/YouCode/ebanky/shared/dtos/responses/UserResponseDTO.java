package com.YouCode.ebanky.shared.dtos.responses;


import com.YouCode.ebanky.entities.enums.Role;
import lombok.Data;
import lombok.Builder;
import java.util.List;
@Data
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private Double monthlyIncome;
    private int creditScore;
    private Double totalSolde;
    private Role role;

}