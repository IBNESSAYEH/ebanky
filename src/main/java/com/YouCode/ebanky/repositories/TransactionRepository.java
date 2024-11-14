package com.YouCode.ebanky.repositories;


import com.YouCode.ebanky.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
