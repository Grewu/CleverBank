package org.example.service.account;


import org.example.models.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void creat(Account account);

    void updateBalanceUser(Integer id, BigDecimal newBalance);

    List<Account> getAccountsByUserID(Integer id);

    void updateBalanceAccount(Integer id, BigDecimal newBalance);

    void closeAccount(int numberOfAccount);

    String getAccountByNumberOfAccount(int numberOfAccount);
}
