package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.Loan;
import com.YouCode.ebanky.repositories.LoanRepository;
import com.YouCode.ebanky.mappers.LoanMapper;
import com.YouCode.ebanky.shared.dtos.requests.LoanRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.LoanResponseDTO;
import com.YouCode.ebanky.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanMapper loanMapper;



    @Override
    @Transactional
    public LoanResponseDTO createLoan(LoanRequestDTO loanRequestDTO) {
        Loan loan = loanMapper.toEntity(loanRequestDTO);
        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.toResponseDTO(savedLoan);
    }

    @Override
    @Transactional
    public LoanResponseDTO approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan with ID " + loanId + " not found"));
        loan.setApproved(true);
        Loan approvedLoan = loanRepository.save(loan);
        return loanMapper.toResponseDTO(approvedLoan);
    }
}
