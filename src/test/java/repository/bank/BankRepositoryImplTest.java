package repository.bank;

import org.example.repository.bank.BankRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BankRepositoryImplTest {

    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;
    private BankRepositoryImpl bankRepository;

    @BeforeEach
    void setUp() {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        bankRepository = new BankRepositoryImpl();
    }

    @Test
    void viewBanksID() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("name")).thenReturn("BankName");
        when(mockResultSet.getString("id")).thenReturn("1");
        bankRepository.viewBanksID();
    }

    @Test
    void getBankById_withValidBankNumber() throws SQLException {
        int validBankNumber = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("name")).thenReturn("BankName");
        String bankName = bankRepository.getBankById(validBankNumber);
        Assertions.assertEquals("BankName", bankName);
    }

    @Test
    void getBankById_withInvalidBankNumber() throws SQLException {
        int invalidBankNumber = 999;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);
        String bankName = bankRepository.getBankById(invalidBankNumber);
        Assertions.assertNull(bankName);
    }
}