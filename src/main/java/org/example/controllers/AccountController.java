package org.example.controllers;

import org.example.models.Account;
import org.example.repository.account.AccountRepository;
import org.example.repository.account.AccountRepositoryImpl;
import org.example.service.account.AccountService;
import org.example.service.account.AccountServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public final class AccountController {
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private final AccountService accountService = new AccountServiceImpl(accountRepository);

    /**
     * Creates a new account.
     *
     * @param account The account to create.
     */
    public void creat(Account account) {
        accountService.creat(account);
    }

    /**
     * Updates the balance for a user's account.
     *
     * @param id            The ID of the account.
     * @param newBalance    The new balance to set.
     * @param userId        The ID of the user.
     * @param accountNumber The account number.
     */
    public void updateBalanceUser(int id, BigDecimal newBalance, int userId, String accountNumber) {
        accountService.updateBalanceUser(id, newBalance, userId, accountNumber);
    }

    /**
     * Retrieves a list of accounts associated with a user.
     *
     * @param id The ID of the user.
     * @return A list of accounts.
     */
    public List<Account> getAccountsByUserID(int id) {
        return accountService.getAccountsByUserID(id);
    }

    /**
     * Updates the balance for an account.
     *
     * @param id            The ID of the account.
     * @param newBalance    The new balance to set.
     * @param numberAccount The account number.
     */
    public void updateBalanceAccount(int id, BigDecimal newBalance, String numberAccount) {
        accountService.updateBalanceAccount(id, newBalance, numberAccount);
    }

    /**
     * Closes an account.
     *
     * @param numberOfAccount The account number to close.
     */
    public void closeAccount(int numberOfAccount) {
        accountService.closeAccount(numberOfAccount);
    }

    /**
     * Retrieves an account by its account number.
     *
     * @param numberOfAccount The account number.
     * @return The account as a string.
     */
    public String getAccountByNumberOfAccount(int numberOfAccount) {
        return accountService.getAccountByNumberOfAccount(numberOfAccount);
    }

    /**
     * Retrieves the opening date of an account.
     *
     * @param numberOfAccount The account number.
     * @return The opening date as a string.
     */
    public String getOpeningDate(int numberOfAccount) {
        return accountService.getOpeningDate(numberOfAccount);
    }
}
