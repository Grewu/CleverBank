package org.example.service.transaction;

import org.example.models.Account;
import org.example.models.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service interface for transaction-related operations.
 */
public interface TransactionService {

    /**
     * Performs a transfer from one bank to another.
     *
     * @param senderAccountId      The sender's account ID.
     * @param transferAmount       The amount to transfer.
     * @param receivingAccountNumber The receiving account's number.
     */
    void getTransferToOtherBank(String senderAccountId, BigDecimal transferAmount, String receivingAccountNumber);

    /**
     * Saves a transaction to the data store.
     *
     * @param transaction The transaction to be saved.
     */
    void save(Transaction transaction);

    /**
     * Retrieves a list of all accounts.
     *
     * @return A list of all accounts.
     */
    List<Account> getAllAccounts();

    /**
     * Deposits a specified amount into the specified account.
     *
     * @param accountId      The ID of the account.
     * @param interestAmount The amount to be deposited.
     */
    void deposit(Integer accountId, BigDecimal interestAmount);

    /**
     * Retrieves the name of the bank associated with a sender's account.
     *
     * @param senderBankAccount The sender's bank account number.
     * @return The name of the bank.
     */
    String getNameOfBank(String senderBankAccount);

    /**
     * Retrieves a list of transactions for a specific account.
     *
     * @param accountNumber The account number for which transactions should be retrieved.
     * @return A list of transactions.
     */
    List<Transaction> getTransactions(String accountNumber);
}
