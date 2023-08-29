package org.example.repository.user;

import org.example.models.User;

import org.example.util.ConnectionManager;
import org.mindrot.jbcrypt.BCrypt;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepositoryImpl implements UserRepository {

    private static final Connection connection = ConnectionManager.open();

    @Override
    public void create(User user) {
        try {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO person (username, password, email,cash) VALUES (?,?,?,?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, hashedPassword);
            statement.setString(3, user.getEmail());
            statement.setBigDecimal(4, user.getCash());
            statement.execute();
            System.out.println("Пользователь был создан");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE username = ?");
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean checkPassword(User user, String password) {
        String hashedPasswordFromDB = user.getPassword();
        return BCrypt.checkpw(password, hashedPasswordFromDB);
    }

    @Override
    public void viewBalance(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE username = ?");
            statement.setString(1, user.getUsername());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    BigDecimal cash = resultSet.getBigDecimal("cash");
                    System.out.println("Ваш баланс " + cash);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BigDecimal checkCash(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE username = ?");
            statement.setString(1, user.getUsername());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBigDecimal("cash");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int getUserId(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM person WHERE username = ?");
            statement.setString(1, user.getUsername());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public void deleteCash(Integer id, BigDecimal initialBalance) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE person SET cash = cash - ? WHERE id = ?");
            statement.setBigDecimal(1, initialBalance);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("С баланса удалено: " + initialBalance);
            } else {
                System.out.println("Не удалось обновить баланс.");
            }
            ;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addCashUser(Integer id, BigDecimal addBalanceUser) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE person SET cash = cash + ? WHERE id = ?");
            statement.setBigDecimal(1, addBalanceUser);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Баланс был по полнен: " + addBalanceUser);
            } else {
                System.out.println("Не удалось обновить баланс.");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
