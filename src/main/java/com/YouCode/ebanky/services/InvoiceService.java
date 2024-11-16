package com.YouCode.ebanky.services;

import com.YouCode.ebanky.shared.dtos.requests.InvoiceRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.InvoiceResponseDTO;

public interface InvoiceService {
    InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO getInvoice(Long id);
    void payInvoice(Long id);
}
