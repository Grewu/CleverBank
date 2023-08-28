package org.example.service.user;

import org.example.models.User;
import org.example.repository.user.UserRepository;


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

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return userRepository.checkPassword(user, password);
    }

}
