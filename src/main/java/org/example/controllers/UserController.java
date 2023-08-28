package org.example.controllers;

import org.example.models.User;
import org.example.repository.user.UserRepository;
import org.example.repository.user.UserRepositoryImpl;
import org.example.service.user.UserService;
import org.example.service.user.UserServiceImpl;

public class UserController {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final UserService userService = new UserServiceImpl(userRepository);

    /**
     * Creates a new user.
     *
     * @param user The user to be created.
     */
    public void create(User user) {
        userService.create(user);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The retrieved user.
     */
    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    /**
     * Checks if the provided password matches the user's password.
     *
     * @param user     The user for which to check the password.
     * @param password The password to check.
     * @return True if the password is correct, false otherwise.
     */
    public boolean checkPassword(User user, String password) {
        return userService.checkPassword(user, password);
    }

    /**
     * Retrieves the balance of a user's account.
     *
     * @param user The user whose balance to retrieve.
     */
}
