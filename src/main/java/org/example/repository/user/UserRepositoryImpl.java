package org.example.repository.user;

import org.example.models.User;

import org.example.util.ConnectionManager;
import org.mindrot.jbcrypt.BCrypt;


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

}
