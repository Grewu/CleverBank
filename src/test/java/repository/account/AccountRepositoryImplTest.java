package repository.account;

import org.example.repository.account.AccountRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class AccountRepositoryImplTest {
    private AccountRepositoryImpl accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepositoryImpl();
    }

    @Test
    void updateBalanceUser_withValidArguments() {
        int userId = 12345;
        BigDecimal balanceChange = BigDecimal.valueOf(100.0);

        Assertions.assertDoesNotThrow(() -> {
            accountRepository.updateBalanceUser(userId, balanceChange,0);
        }, "updateBalanceUser should not throw an exception for valid arguments");
    }

    @Test
    void updateBalanceAccount_withValidArguments() {
        int accountId = 12345;
        BigDecimal balanceChange = BigDecimal.valueOf(-50.0);

        Assertions.assertDoesNotThrow(() -> {
            accountRepository.updateBalanceAccount(accountId, balanceChange);
        }, "updateBalanceAccount should not throw an exception for valid arguments");
    }

    @Test
    void closeAccount_withValidAccountNumber() {
        int accountNumber = 12345;

        Assertions.assertDoesNotThrow(() -> {
            accountRepository.closeAccount(accountNumber);
        }, "closeAccount should not throw an exception for a valid account number");
    }


    @Test
    void updateBalanceAccount() {
        int accountId = 12345;
        BigDecimal balanceChange = BigDecimal.valueOf(-50.0);

        Assertions.assertDoesNotThrow(() -> {
            accountRepository.updateBalanceAccount(accountId, balanceChange);
        }, "updateBalanceAccount should not throw an exception for valid arguments");
    }

    @Test
    void closeAccount() {
        int accountNumber = 12345;

        Assertions.assertDoesNotThrow(() -> {
            accountRepository.closeAccount(accountNumber);
        }, "closeAccount should not throw an exception for a valid account number");
    }

}
