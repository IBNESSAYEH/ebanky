package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.Invoice;
import com.YouCode.ebanky.repositories.InvoiceRepository;
import com.YouCode.ebanky.mappers.InvoiceMapper;
import com.YouCode.ebanky.shared.dtos.requests.InvoiceRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.InvoiceResponseDTO;
import com.YouCode.ebanky.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    @Transactional
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice = invoiceMapper.toEntity(invoiceRequestDTO);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toResponseDTO(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Invoice with ID " + id + " not found"));
        return invoiceMapper.toResponseDTO(invoice);
    }

    @Override
    public void payInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Invoice with ID " + id + " not found"));
        if (!invoice.isPaid()) {
            invoice.pay();
            invoiceRepository.save(invoice);
        } else {
            throw new IllegalStateException("Invoice with ID " + id + " is already paid");
        }
    }
}
