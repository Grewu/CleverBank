package org.example.service.transaction;

import org.example.models.Account;
import org.example.models.Transaction;
import org.example.repository.transaction.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of the TransactionService interface.
 */
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    /**
     * Constructs a TransactionServiceImpl with the provided TransactionRepository.
     *
     * @param transactionRepository The TransactionRepository to be used.
     */
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void getTransferToOtherBank(String senderAccountId, BigDecimal transferAmount, String receivingAccountNumber) {
        transactionRepository.getTransferToOtherBank(senderAccountId, transferAmount, receivingAccountNumber);
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Account> getAllAccounts() {
        return transactionRepository.getAllAccounts();
    }

    @Override
    public void deposit(Integer accountId, BigDecimal interestAmount) {
        transactionRepository.deposit(accountId, interestAmount);
    }

    @Override
    public String getNameOfBank(String senderBankAccount) {
        return transactionRepository.getNameOfBank(senderBankAccount);
    }

    @Override
    public List<Transaction> getTransactions(String accountNumber) {
        return transactionRepository.getTransactions(accountNumber);
    }
}
