package org.example.service.user;

import org.example.models.User;

import java.math.BigDecimal;

public interface UserService {
    void create(User user);

    User getUserByUsername(String username);

    boolean checkPassword(User user, String password);

}
