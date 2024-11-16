package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.shared.dtos.requests.InvoiceRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.InvoiceResponseDTO;
import com.YouCode.ebanky.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public InvoiceResponseDTO createInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        return invoiceService.createInvoice(invoiceRequestDTO);
    }

    @GetMapping("/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }

    @PostMapping("/{id}/pay")
    public void payInvoice(@PathVariable Long id) {
        invoiceService.payInvoice(id);
    }
}

