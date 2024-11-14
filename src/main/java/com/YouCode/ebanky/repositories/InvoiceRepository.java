package com.YouCode.ebanky.repositories;


import com.YouCode.ebanky.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
