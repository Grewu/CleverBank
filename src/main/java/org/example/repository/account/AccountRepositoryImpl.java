package org.example.repository.account;

import org.example.models.Account;
import org.example.util.db.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class AccountRepositoryImpl implements AccountRepository {
    private static final Connection connection = ConnectionManager.open();

}
