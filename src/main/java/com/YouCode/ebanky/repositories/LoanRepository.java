package com.YouCode.ebanky.repositories;


import com.YouCode.ebanky.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
