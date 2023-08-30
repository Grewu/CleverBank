package org.example.repository.account;

import org.example.models.Account;
import org.example.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the AccountRepository interface.
 */
public final class AccountRepositoryImpl implements AccountRepository {
    private static final Connection connection = ConnectionManager.open();

    @Override
    public void creat(Account account) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO account (user_id, bank_id, balance, account_number, name_user, opening_time) VALUES (?,?,?,?,?,?)");
            statement.setInt(1, account.getIdUser());
            statement.setInt(2, account.getIdBank());
            statement.setBigDecimal(3, account.getBalance());
            statement.setString(4, account.getAccountNumber());
            statement.setString(5, account.getBankName());

            statement.setString(6, String.valueOf(LocalTime.now()));

            statement.execute();
            System.out.println("The account has been successfully created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void updateBalanceUser(Integer id, BigDecimal newBalance, int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE account SET balance = balance + ? WHERE user_id = ?");
            PreparedStatement personUpdateStatement = connection.prepareStatement(
                    "UPDATE person SET cash = cash - ? WHERE id = ?");

            personUpdateStatement.setBigDecimal(1, newBalance);
            personUpdateStatement.setInt(2, userId);
            personUpdateStatement.executeUpdate();

            statement.setBigDecimal(1, newBalance);
            statement.setInt(2, id);
            statement.executeUpdate();

            System.out.println("The balance has been successfully updated.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getAccountsByUserID(Integer userID) {
        List<Account> accounts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE user_id = ?")) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idUser = resultSet.getInt("id");
                    int user_Id = resultSet.getInt("user_id");
                    int bank_Id = resultSet.getInt("bank_id");
                    BigDecimal balance = resultSet.getBigDecimal("balance");
                    Account account = new Account(idUser, balance, user_Id, bank_Id);
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public void updateBalanceAccount(Integer id, BigDecimal newBalance) {
        try {
            updateAccountBalance(connection, id, newBalance);
            updatePersonCash(connection, id, newBalance);
            System.out.println("The balance has been updated.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateAccountBalance(Connection connection, Integer id, BigDecimal newBalance) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE account SET balance = balance - ? WHERE id = ?")) {
            statement.setBigDecimal(1, newBalance);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    private void updatePersonCash(Connection connection, Integer id, BigDecimal newBalance) throws SQLException {
        try (PreparedStatement getUserIdStatement = connection.prepareStatement(
                "SELECT user_id FROM account WHERE id = ?");
             PreparedStatement updatePersonStatement = connection.prepareStatement(
                     "UPDATE person SET cash = cash + ? WHERE id = ?")) {
            getUserIdStatement.setInt(1, id);
            ResultSet resultSet = getUserIdStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                updatePersonStatement.setBigDecimal(1, newBalance);
                updatePersonStatement.setInt(2, userId);
                updatePersonStatement.executeUpdate();
            }
        }
    }

    @Override
    public void closeAccount(int numberOfAccount) {
        try {
            BigDecimal balance = retrieveAccountBalance(connection, numberOfAccount);
            if (balance != null) {
                int userId = retrieveUserId(connection, numberOfAccount);
                updatePersonCash(connection, userId, balance);
                deleteAccount(connection, numberOfAccount);
                System.out.println("Account closed successfully");
            } else {
                System.out.println("Account not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error " + e.getMessage());
        }
    }

    private BigDecimal retrieveAccountBalance(Connection connection, int accountId) throws SQLException {
        try (PreparedStatement getBalanceStatement = connection.prepareStatement(
                "SELECT balance FROM account WHERE id = ?")) {
            getBalanceStatement.setInt(1, accountId);
            ResultSet balanceResult = getBalanceStatement.executeQuery();
            if (balanceResult.next()) {
                return balanceResult.getBigDecimal("balance");
            }
            return null;
        }
    }

    private int retrieveUserId(Connection connection, int accountId) throws SQLException {
        try (PreparedStatement getUserIdStatement = connection.prepareStatement(
                "SELECT user_id FROM account WHERE id = ?")) {
            getUserIdStatement.setInt(1, accountId);
            ResultSet resultSet = getUserIdStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            }
            return 0;
        }
    }

    private void updatePersonCash(Connection connection, int userId, BigDecimal balance) throws SQLException {
        try (PreparedStatement updatePersonStatement = connection.prepareStatement(
                "UPDATE person SET cash = cash + ? WHERE id = ?")) {
            updatePersonStatement.setBigDecimal(1, balance);
            updatePersonStatement.setInt(2, userId);
            updatePersonStatement.executeUpdate();
        }
    }

    private void deleteAccount(Connection connection, int accountId) throws SQLException {
        try (PreparedStatement deleteAccountStatement = connection.prepareStatement(
                "UPDATE account SET closing_time = ? WHERE id = ?")) {
            deleteAccountStatement.setString(1, String.valueOf(LocalTime.now()));
            deleteAccountStatement.setInt(2, accountId);
            deleteAccountStatement.executeUpdate();
        }
    }

    @Override
    public String getAccountByNumberOfAccount(int numberOfAccount) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE id = ?");
            statement.setInt(1, numberOfAccount);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("account_number");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
