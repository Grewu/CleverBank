package org.example.service.account;

import org.example.models.Account;
import org.example.repository.account.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

public final class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


}
