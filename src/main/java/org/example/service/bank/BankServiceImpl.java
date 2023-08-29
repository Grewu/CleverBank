package org.example.service.bank;

import org.example.repository.bank.BankRepository;
import org.example.util.log.Loggable;


public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    @Loggable
    public void viewBanksID() {
        bankRepository.viewBanksID();
    }

    @Override
    @Loggable
    public String getBankById(Integer bankNumber) {
       return bankRepository.getBankById(bankNumber);
    }
}
