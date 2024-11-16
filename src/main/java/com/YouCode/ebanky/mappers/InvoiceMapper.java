package com.YouCode.ebanky.mappers;

import com.YouCode.ebanky.entities.Invoice;
import com.YouCode.ebanky.shared.dtos.requests.InvoiceRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.InvoiceResponseDTO;
import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.repositories.AccountRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class InvoiceMapper {

    @Autowired
    protected AccountRepository accountRepository;

    public Invoice toEntity(InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice = new Invoice();
        invoice.setAmountDue(invoiceRequestDTO.getAmountDue());


          invoice.setDueDate(LocalDateTime.parse(invoiceRequestDTO.getDueDate()));
        Account account = accountRepository.findById(invoiceRequestDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        invoice.setAccount(account);

        return invoice;
    }


    public InvoiceResponseDTO toResponseDTO(Invoice invoice) {
        InvoiceResponseDTO invoiceResponseDTO = new InvoiceResponseDTO();
        invoiceResponseDTO.setId(invoice.getId());
          invoiceResponseDTO.setAmountDue(invoice.getAmountDue());


 invoiceResponseDTO.setDueDate(invoice.getDueDate() != null ? invoice.getDueDate().toString() : null);
        invoiceResponseDTO.setAccountId(invoice.getAccount() != null ? invoice.getAccount().getId() : null);

        return invoiceResponseDTO;
    }
}
