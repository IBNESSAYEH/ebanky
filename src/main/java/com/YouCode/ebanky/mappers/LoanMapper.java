package com.YouCode.ebanky.mappers;

import com.YouCode.ebanky.entities.Loan;
import com.YouCode.ebanky.shared.dtos.requests.LoanRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.LoanResponseDTO;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LoanMapper {

    @Autowired
    protected UserRepository userRepository;

    public Loan toEntity(LoanRequestDTO loanRequestDTO) {
        Loan loan = new Loan();
        loan.setPrincipal(loanRequestDTO.getPrincipal());
        loan.setInterestRate(loanRequestDTO.getInterestRate());
           loan.setTermMonths(loanRequestDTO.getTermMonths());


        User user = userRepository.findById(loanRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        loan.setUser(user);

        return loan;
    }


    public LoanResponseDTO toResponseDTO(Loan loan) {
        LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
           loanResponseDTO.setId(loan.getId());
         loanResponseDTO.setPrincipal(loan.getPrincipal());

    loanResponseDTO.setInterestRate(loan.getInterestRate());
        loanResponseDTO.setTermMonths(loan.getTermMonths());

loanResponseDTO.setApproved(loan.isApproved());

        return loanResponseDTO;
    }
}
