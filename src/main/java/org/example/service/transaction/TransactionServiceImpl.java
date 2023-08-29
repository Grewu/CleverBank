package org.example.service.transaction;

import org.example.models.Account;
import org.example.models.Transaction;
import org.example.repository.transaction.TransactionRepository;
import org.example.util.log.Loggable;

import java.math.BigDecimal;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Loggable
    public void getTransferToOtherBank(String senderAccountId, BigDecimal transferAmount, String receivingAccountNumber) {
        transactionRepository.getTransferToOtherBank(senderAccountId,transferAmount,receivingAccountNumber);
    }

    @Override
    @Loggable
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    @Loggable
    public List<Account> getAllAccounts() {
       return transactionRepository.getAllAccounts();
    }

    @Override
    @Loggable
    public void deposit(Integer accountId, BigDecimal interestAmount) {
        transactionRepository.deposit(accountId,  interestAmount);
    }

    @Override
    @Loggable
    public String getNameOfBank(String senderBankAccount) {
        return transactionRepository.getNameOfBank(senderBankAccount);
    }

    @Override
    @Loggable
    public List<Transaction> getTransactions() {
        return  transactionRepository.getTransactions();
    }


}
