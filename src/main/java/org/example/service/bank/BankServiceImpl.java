package org.example.service.bank;

import org.example.repository.bank.BankRepository;


public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void viewBanksID() {
        bankRepository.viewBanksID();
    }

    @Override
    public String getBankById(Integer bankNumber) {
       return bankRepository.getBankById(bankNumber);

    }
}
