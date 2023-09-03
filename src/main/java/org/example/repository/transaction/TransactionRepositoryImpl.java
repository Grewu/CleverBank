package org.example.repository.transaction;


import org.example.models.Account;
import org.example.models.Transaction;
import org.example.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TransactionRepository interface.
 */
public class TransactionRepositoryImpl implements TransactionRepository {
    private static final Connection connection = ConnectionManager.open();

    @Override
    public void getTransferToOtherBank(String senderAccountId, BigDecimal transferAmount,
                                       String receivingAccountNumber) {
        try (Connection connection = ConnectionManager.open()) {
            decreaseSenderBalance(connection, senderAccountId, transferAmount);
            increaseReceiverBalance(connection, receivingAccountNumber, transferAmount);
            insertTransaction(connection, senderAccountId, receivingAccountNumber, transferAmount);
            System.out.println("The transaction was successfully conducted.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void decreaseSenderBalance(Connection connection, String senderAccountId, BigDecimal amount)
            throws SQLException {
        try (PreparedStatement decreaseStatement = connection.prepareStatement(
                "UPDATE account SET balance = balance - ? WHERE account_number = ?")) {
            decreaseStatement.setBigDecimal(1, amount);
            decreaseStatement.setString(2, senderAccountId);
            decreaseStatement.executeUpdate();
        }
    }

    private void increaseReceiverBalance(Connection connection, String receiverAccountNumber, BigDecimal amount)
            throws SQLException {
        try (PreparedStatement increaseStatement = connection.prepareStatement(
                "UPDATE account SET balance = balance + ? WHERE account_number = ?")) {
            increaseStatement.setBigDecimal(1, amount);
            increaseStatement.setString(2, receiverAccountNumber);
            increaseStatement.executeUpdate();
        }
    }

    private void insertTransaction(Connection connection, String senderAccountId, String receiverAccountNumber,
                                   BigDecimal amount) throws SQLException {
        try (PreparedStatement insertTransactionStatement = connection.prepareStatement(
                "INSERT INTO transactions (sender_account_number,receiver_account_number," +
                        "transaction_amount,transaction_timestamp,transaction_type) VALUES (?, ?, ?,?,'transfer')")) {
            insertTransactionStatement.setString(1, senderAccountId);
            insertTransactionStatement.setString(2, receiverAccountNumber);
            insertTransactionStatement.setBigDecimal(3, amount);
            insertTransactionStatement.setString(4, String.valueOf(LocalDate.now()));
            insertTransactionStatement.executeUpdate();
        }
    }

    @Override
    public void save(Transaction transaction) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO transactions (sender_account_number, receiver_account_number, transaction_amount," +
                        " transaction_type, transaction_timestamp) VALUES (?, ?, ?,?, ?)")) {
            String senderAccountNumber = transaction.getSenderAccountNumber() != null ?
                    transaction.getSenderAccountNumber() : "null_sender";
            String receiverAccountNumber = transaction.getReceiverAccountNumber() != null ?
                    transaction.getReceiverAccountNumber() : "null_receiver";
            statement.setString(1, senderAccountNumber);
            statement.setString(2, receiverAccountNumber);
            statement.setBigDecimal(3, transaction.getAmount());

            statement.setString(4, "money transfer");
            statement.setTimestamp(5, Timestamp.valueOf(transaction.getTimestamp()));
            statement.executeUpdate();
        } catch (SQLException ex) {

            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deposit(Integer accountId, BigDecimal interestAmount) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE account SET balance = balance + ? WHERE id = ? AND closing_time IS NULL")) {
            statement.setBigDecimal(1, interestAmount);
            statement.setInt(2, accountId);
            int rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM account");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int accountId = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int bankId = resultSet.getInt("bank_id");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                String account_number = resultSet.getString("account_number");
                String name_user = resultSet.getString("name_user");
                Account account = new Account(accountId, userId, bankId, balance, account_number, name_user);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return accounts;
    }

    @Override
    public String getNameOfBank(String senderBankAccount) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM account WHERE account_number = ? ")) {
            statement.setString(1, senderBankAccount);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name_user");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return senderBankAccount;
    }

    @Override
    public List<Transaction> getTransactions(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM transactions WHERE sender_account_number = ? ")) {
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String date = resultSet.getString("transaction_timestamp");
                String type = resultSet.getString("transaction_type");
                BigDecimal amount = resultSet.getBigDecimal("transaction_amount");
                Transaction transaction = new Transaction(date, type, amount);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
}