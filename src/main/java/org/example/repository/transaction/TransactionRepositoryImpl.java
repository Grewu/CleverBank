package org.example.repository.transaction;


import org.example.util.ConnectionManager;
import java.sql.*;


public class TransactionRepositoryImpl implements TransactionRepository {
    private static final Connection connection = ConnectionManager.open();


}
