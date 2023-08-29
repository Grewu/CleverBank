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

    @Override
    public void creat(Account account) {
        accountRepository.create(account);
    }
    @Override
    public void updateBalanceUser(Integer id, BigDecimal newBalance) {
        accountRepository.updateBalanceUser(id,newBalance);
    }

    @Override
    public List<Account> getAccountsByUserID(Integer id) {
        return  accountRepository.getAccountsByUserID(id);
    }

    @Override
    public void updateBalanceAccount(Integer id, BigDecimal newBalance) {
        accountRepository.updateBalanceAccount(id,newBalance);
    }
    @Override
    public void closeAccount(int numberOfAccount) {
        accountRepository.closeAccount(numberOfAccount);
    }

    @Override
    public String getAccountByNumberOfAccount(int numberOfAccount) {
        return  accountRepository.getAccountByNumberOfAccount(numberOfAccount);
    }

}
