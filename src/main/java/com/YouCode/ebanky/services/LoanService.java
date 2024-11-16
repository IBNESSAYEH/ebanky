package com.YouCode.ebanky.services;

import com.YouCode.ebanky.shared.dtos.requests.LoanRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.LoanResponseDTO;

public interface LoanService {
    LoanResponseDTO createLoan(LoanRequestDTO loanRequestDTO);
    LoanResponseDTO approveLoan(Long loanId);
}
