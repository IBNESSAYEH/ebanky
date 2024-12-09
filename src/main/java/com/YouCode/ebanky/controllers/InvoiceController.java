package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.shared.dtos.requests.InvoiceRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.InvoiceResponseDTO;
import com.YouCode.ebanky.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@AllArgsConstructor
public class InvoiceController {

    private InvoiceService invoiceService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PostMapping
    public InvoiceResponseDTO createInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        return invoiceService.createInvoice(invoiceRequestDTO);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or (hasRole('USER') and @invoiceService.isOwner(#id, authentication))")
    @GetMapping("/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE')")
    @PostMapping("/{id}/pay")
    public void payInvoice(@PathVariable Long id) {
        invoiceService.payInvoice(id);
    }
}
