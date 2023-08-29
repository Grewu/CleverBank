package org.example.repository.account;

import org.example.models.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface defining methods for managing accounts.
 */
public interface AccountRepository {

    /**
     * Creates a new account.
     *
     * @param account The account to be created.
     */
    void create(Account account);

    /**
     * Updates the balance of a user's account.
     *
     * @param id         The ID of the account.
     * @param newBalance The new balance to set.
     */
    void updateBalanceUser(Integer id, BigDecimal newBalance);

    /**
     * Retrieves a list of accounts associated with a user.
     *
     * @param id The ID of the user.
     * @return A list of user's accounts.
     */
    List<Account> getAccountsByUserID(Integer id);

    /**
     * Updates the balance of an account.
     *
     * @param id         The ID of the account.
     * @param newBalance The new balance to set.
     */
    void updateBalanceAccount(Integer id, BigDecimal newBalance);

    /**
     * Closes an account.
     *
     * @param numberOfAccount The number of the account to close.
     */
    void closeAccount(int numberOfAccount);

    /**
     * Retrieves account information by account number.
     *
     * @param numberOfAccount The number of the account to retrieve.
     * @return Account information as a string.
     */
    String getAccountByNumberOfAccount(int numberOfAccount);
}
