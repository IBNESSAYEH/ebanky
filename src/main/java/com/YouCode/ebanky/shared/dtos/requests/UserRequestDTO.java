package com.YouCode.ebanky.shared.dtos.requests;

import com.YouCode.ebanky.entities.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotNull
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 characters")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters")
    private String lastName;

    @NotNull
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    private String password;

    @NotNull
    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @PositiveOrZero(message = "Monthly income must be a positive value")
    private Double monthlyIncome;

    @Min(value = 0, message = "Credit score must be at least 0")
    @Max(value = 850, message = "Credit score must be no more than 850")
    private int creditScore;

    @NotNull(message = "Role cannot be null")
    private Role role;

    @NotNull
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;
}
