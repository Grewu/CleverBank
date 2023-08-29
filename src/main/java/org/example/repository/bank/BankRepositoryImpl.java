package org.example.repository.bank;



import org.example.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BankRepositoryImpl implements BankRepository {
    private static final Connection connection = ConnectionManager.open();

    @Override
    public void viewBanksID() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM bank");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("name");
                String ids = resultSet.getString("id");
                System.out.println(ids + " " + username);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBankById(Integer bankNumber) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank WHERE id = ?")) {
            statement.setInt(1, bankNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
