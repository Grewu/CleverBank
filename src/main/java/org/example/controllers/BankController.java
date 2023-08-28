package org.example.controllers;

import org.example.repository.bank.BankRepository;
import org.example.repository.bank.BankRepositoryImpl;
import org.example.service.bank.BankService;
import org.example.service.bank.BankServiceImpl;

public final class BankController {
    private final BankRepository bankRepository = new BankRepositoryImpl();
    private final BankService bankService = new BankServiceImpl(bankRepository);
}