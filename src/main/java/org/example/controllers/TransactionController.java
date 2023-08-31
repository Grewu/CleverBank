package org.example.controllers;

import org.example.models.Account;
import org.example.models.Transaction;
import org.example.repository.transaction.TransactionRepository;
import org.example.repository.transaction.TransactionRepositoryImpl;
import org.example.service.transaction.TransactionService;
import org.example.service.transaction.TransactionServiceImpl;
import org.example.util.config.ConfigReader;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controller class responsible for handling transaction-related operations.
 */
public final class TransactionController {
    private final TransactionRepository transactionRepository = new TransactionRepositoryImpl();
    private final TransactionService transactionService = new TransactionServiceImpl(transactionRepository);

    /**
     * Performs a transfer from one bank to another.
     *
     * @param senderAccountNumber   The sender's account number.
     * @param transferAmount        The amount to transfer.
     * @param receivingAccountNumber The receiving account's number.
     */
    public void getTransferToOtherBank(String senderAccountNumber, BigDecimal transferAmount, String receivingAccountNumber) {
        transactionService.getTransferToOtherBank(senderAccountNumber, transferAmount, receivingAccountNumber);
    }

    /**
     * Starts the periodic calculation of interest for accounts.
     * Runs every minute to calculate interest and deposit it into eligible accounts.
     */
    public synchronized void startInterestCalculation() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                BigDecimal interestRate = ConfigReader.getConfiguredInterestRate();

                List<Account> accounts = transactionService.getAllAccounts();
                for (Account account : accounts) {
                    BigDecimal currentBalance = account.getBalance();
                    if (currentBalance.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("No money in the account");
                        continue;
                    }
                    BigDecimal interestAmount = currentBalance.multiply(interestRate);
                    transactionService.deposit(account.getAccountId(), interestAmount);
                    Transaction transaction = new Transaction(account.getAccountId(),"sender","receiver", interestAmount, "transfer", LocalDateTime.now());
                    transactionService.save(transaction);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    /**
     * Retrieves the name of the bank associated with a sender's account.
     *
     * @param senderBankAccount The sender's bank account number.
     * @return The name of the bank.
     */
    public String getNameOfBank(String senderBankAccount) {
        return transactionService.getNameOfBank(senderBankAccount);
    }

    /**
     * Retrieves a list of transactions.
     *
     * @return A list of transactions.
     */
    public List<Transaction> getTransactions(String accountNumber) {
        return transactionService.getTransactions(accountNumber);
    }
}
