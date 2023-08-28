package org.example.repository.bank;



import org.example.util.ConnectionManager;

import java.sql.Connection;


public class BankRepositoryImpl implements BankRepository {
    private static final Connection connection = ConnectionManager.open();


}
