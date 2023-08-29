package org.example.service.user;

import org.example.models.User;
import org.example.repository.user.UserRepository;
import org.example.util.log.Loggable;

import java.math.BigDecimal;


public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.create(user);
    }

    @Override
    @Loggable
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    @Loggable
    public boolean checkPassword(User user, String password) {
        return userRepository.checkPassword(user, password);
    }
    @Override
    @Loggable
    public void viewBalance(User user) {
        userRepository.viewBalance(user);
    }

    @Override
    @Loggable
    public BigDecimal checkCash(User user) {
        return userRepository.checkCash(user);
    }

    @Override
    @Loggable
    public int getUserId(User user) {
        return userRepository.getUserId(user);
    }

    @Override
    @Loggable
    public void deleteCash(Integer id, BigDecimal initialBalance) {
        userRepository.deleteCash(id, initialBalance);
    }

    @Override
    @Loggable
    public void addCashUser(Integer userId, BigDecimal addBalanceUser) {
        userRepository.addCashUser(userId, addBalanceUser);
    }

}
