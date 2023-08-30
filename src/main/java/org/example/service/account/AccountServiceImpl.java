package org.example.service.account;

import org.example.models.Account;
import org.example.repository.account.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of the AccountService interface.
 */
public final class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    /**
     * Constructs a new instance of AccountServiceImpl with the provided AccountRepository.
     *
     * @param accountRepository The repository responsible for account data storage and retrieval.
     */
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void creat(Account account) {
        accountRepository.creat(account);
    }

    @Override
    public void updateBalanceUser(Integer id, BigDecimal newBalance, int userId) {
        accountRepository.updateBalanceUser(id, newBalance, userId);
    }

    @Override
    public List<Account> getAccountsByUserID(Integer id) {
        return accountRepository.getAccountsByUserID(id);
    }

    @Override
    public void updateBalanceAccount(Integer id, BigDecimal newBalance) {
        accountRepository.updateBalanceAccount(id, newBalance);
    }

    @Override
    public void closeAccount(int numberOfAccount) {
        accountRepository.closeAccount(numberOfAccount);
    }

    @Override
    public String getAccountByNumberOfAccount(int numberOfAccount) {
        return accountRepository.getAccountByNumberOfAccount(numberOfAccount);
    }
}
