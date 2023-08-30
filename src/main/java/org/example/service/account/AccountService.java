package org.example.service.account;

import org.example.models.Account;
import java.math.BigDecimal;
import java.util.List;

/**
 * Service interface for account operations.
 */
public interface AccountService {

    /**
     * Creates a new account.
     *
     * @param account The account to be created.
     */
    void creat(Account account);

    /**
     * Updates the balance of a user's account.
     *
     * @param id        The ID of the account to update.
     * @param newBalance The new balance for the account.
     * @param userId    The ID of the associated user.
     */
    void updateBalanceUser(Integer id, BigDecimal newBalance, int userId);

    /**
     * Retrieves a list of accounts associated with a user.
     *
     * @param id The ID of the user.
     * @return A list of accounts associated with the user.
     */
    List<Account> getAccountsByUserID(Integer id);

    /**
     * Updates the balance of an account.
     *
     * @param id        The ID of the account to update.
     * @param newBalance The new balance for the account.
     */
    void updateBalanceAccount(Integer id, BigDecimal newBalance);

    /**
     * Closes an account.
     *
     * @param numberOfAccount The number of the account to close.
     */
    void closeAccount(int numberOfAccount);

    /**
     * Retrieves the account information by its account number.
     *
     * @param numberOfAccount The number of the account.
     * @return The account information as a string.
     */
    String getAccountByNumberOfAccount(int numberOfAccount);
}
