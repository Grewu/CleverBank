package org.example.controllers;

import org.example.repository.bank.BankRepository;
import org.example.repository.bank.BankRepositoryImpl;
import org.example.service.bank.BankService;
import org.example.service.bank.BankServiceImpl;

public final class BankController {
    private final BankRepository bankRepository = new BankRepositoryImpl();
    private final BankService bankService = new BankServiceImpl(bankRepository);

    /**
     * Displays the IDs of all banks.
     */
    public void viewBanksID() {
        bankService.viewBanksID();
    }

    /**
     * Retrieves information about a bank by its ID.
     *
     * @param bankNumber The ID of the bank.
     * @return Information about the bank.
     */
    public String getBankById(Integer bankNumber) {
        return bankService.getBankById(bankNumber);
    }
}