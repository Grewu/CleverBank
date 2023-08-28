package org.example.controllers;

import org.example.repository.account.AccountRepository;
import org.example.repository.account.AccountRepositoryImpl;
import org.example.service.account.AccountService;
import org.example.service.account.AccountServiceImpl;

public final class AccountController {
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private final AccountService accountService = new AccountServiceImpl(accountRepository);

}