package repository.transaction;

import org.example.repository.transaction.TransactionRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TransactionRepositoryImplTest {
    private TransactionRepositoryImpl transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepositoryImpl();
    }

    @Test
    void getTransferToOtherBank() {
        BigDecimal transferAmount = BigDecimal.valueOf(100.0);
        String senderAccountId = "senderAccountId";
        String receivingAccountNumber = "receivingAccountNumber";
        TransactionRepositoryImpl mockTransactionRepository = mock(TransactionRepositoryImpl.class);
        mockTransactionRepository.getTransferToOtherBank(senderAccountId, transferAmount, receivingAccountNumber);
        verify(mockTransactionRepository).getTransferToOtherBank(eq(senderAccountId), eq(transferAmount), eq(receivingAccountNumber));
    }

    @Test
    void deposit() {
        int accountId = 1;
        BigDecimal interestAmount = BigDecimal.valueOf(50);
        Assertions.assertDoesNotThrow(() -> {
            transactionRepository.deposit(accountId, interestAmount);
        });
    }


    @Test
    void getNameOfBank_withInvalidBankAccount_shouldReturnBankAccount()  {
        String senderBankAccount = "invalidAccount";
        String bankName = transactionRepository.getNameOfBank(senderBankAccount);

        Assertions.assertEquals("invalidAccount", bankName);
    }

}
