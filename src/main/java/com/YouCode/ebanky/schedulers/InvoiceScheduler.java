package com.YouCode.ebanky.schedulers;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.Invoice;
import com.YouCode.ebanky.repositories.AccountRepository;
import com.YouCode.ebanky.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class InvoiceScheduler {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void processDueInvoices() {
        List<Invoice> dueInvoices = invoiceRepository.findAllByDueDateBeforeAndPaidFalse(LocalDateTime.now());

        for (Invoice invoice : dueInvoices) {
            Account account = invoice.getAccount();

            if (account != null) {
                Double amountDue = Double.parseDouble(invoice.getAmountDue());

                if (account.getBalance() >= amountDue) {
                    account.setBalance(account.getBalance() - amountDue);

                    invoice.setPaid(true);

                    accountRepository.save(account);
                    invoiceRepository.save(invoice);
                }
            }
        }
    }
}
