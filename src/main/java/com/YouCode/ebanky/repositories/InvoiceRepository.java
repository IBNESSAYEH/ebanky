package com.YouCode.ebanky.repositories;

import com.YouCode.ebanky.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Account, Long> {
}
