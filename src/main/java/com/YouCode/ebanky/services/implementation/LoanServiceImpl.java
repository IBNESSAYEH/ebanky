package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.Loan;
import com.YouCode.ebanky.repositories.LoanRepository;
import com.YouCode.ebanky.shared.dtos.requests.LoanRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.LoanResponseDTO;
import com.YouCode.ebanky.services.LoanService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    private ModelMapper modelMapper;

    @Override
    @Transactional
    public LoanResponseDTO createLoan(LoanRequestDTO loanRequestDTO) {
        Loan loan = modelMapper.map(loanRequestDTO, Loan.class);
        Loan savedLoan = loanRepository.save(loan);
        return modelMapper.map(savedLoan, LoanResponseDTO.class);
    }

    @Override
    @Transactional
    public LoanResponseDTO approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan with ID " + loanId + " not found"));

        UserDetails authenticatedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authenticatedUser != null) {
            String userRole = authenticatedUser.getAuthorities().stream()
                    .findFirst()
                    .map(authority -> authority.getAuthority())
                    .orElse("");

            if (userRole.equals("ROLE_ADMIN") || userRole.equals("ROLE_EMPLOYEE")) {
                loan.setApproved(true);
                Loan approvedLoan = loanRepository.save(loan);
                return modelMapper.map(approvedLoan, LoanResponseDTO.class);
            } else {
                throw new SecurityException("You are not authorized to approve loans");
            }
        }
        throw new SecurityException("User not authenticated");
    }
}
