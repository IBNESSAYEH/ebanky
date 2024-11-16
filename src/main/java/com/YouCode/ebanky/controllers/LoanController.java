package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.shared.dtos.requests.LoanRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.LoanResponseDTO;
import com.YouCode.ebanky.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public LoanResponseDTO createLoan(@RequestBody LoanRequestDTO loanRequestDTO) {
        return loanService.createLoan(loanRequestDTO);
    }

    @PostMapping("/{loanId}/approve")
    public LoanResponseDTO approveLoan(@PathVariable Long loanId) {
        return loanService.approveLoan(loanId);
    }
}
