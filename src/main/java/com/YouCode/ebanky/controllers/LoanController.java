package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.shared.dtos.requests.LoanRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.LoanResponseDTO;
import com.YouCode.ebanky.services.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@AllArgsConstructor
public class LoanController {

    private LoanService loanService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public LoanResponseDTO createLoan(@RequestBody LoanRequestDTO loanRequestDTO) {
        return loanService.createLoan(loanRequestDTO);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PostMapping("/{loanId}/approve")
    public LoanResponseDTO approveLoan(@PathVariable Long loanId) {
        return loanService.approveLoan(loanId);
    }
}
