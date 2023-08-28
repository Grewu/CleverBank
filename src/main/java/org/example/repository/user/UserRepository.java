package org.example.repository.user;

import org.example.models.User;

import java.math.BigDecimal;

/**
 * Interface defining methods for managing user-related operations.
 */
public interface UserRepository {

    /**
     * Creates a new user.
     *
     * @param user The user to be created.
     */
    void create(User user);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The retrieved user.
     */
    User getUserByUsername(String username);

    /**
     * Checks if the provided password matches the user's password.
     *
     * @param user     The user for which to check the password.
     * @param password The password to check.
     * @return True if the password is correct, false otherwise.
     */
    boolean checkPassword(User user, String password);

}
