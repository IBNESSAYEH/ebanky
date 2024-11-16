package com.YouCode.ebanky.repositories;

import com.YouCode.ebanky.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByDueDateBeforeAndPaidFalse(LocalDateTime dueDate);

}
