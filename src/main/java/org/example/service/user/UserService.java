package org.example.service.user;

import org.example.models.User;

import java.math.BigDecimal;

public interface UserService {
    void create(User user);

    User getUserByUsername(String username);

    boolean checkPassword(User user, String password);
    void viewBalance(User user);

    BigDecimal checkCash(User user);

    int getUserId(User user);

    void deleteCash(Integer id, BigDecimal initialBalance);

    void addCashUser(Integer userId, BigDecimal addBalanceUser);
}
