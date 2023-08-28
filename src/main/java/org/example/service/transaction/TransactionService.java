package org.example.service.transaction;

import org.example.models.Account;
import org.example.models.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    void getTransferToOtherBank(String senderAccountId, BigDecimal transferAmount, String receivingAccountNumber);

    void save(Transaction transaction);

    List<Account> getAllAccounts();

    void deposit(Integer accountId, BigDecimal interestAmount);

    String getNameOfBank(String senderBankAccount);

    List<Transaction> getTransactions();

}
