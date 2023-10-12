package repository.user;

import org.example.models.User;
import org.example.repository.user.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;


class UserRepositoryImplTest {
    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    void create() {
        User user = new User();
        user.setUsername("newuser");
        user.setPassword("newpassword");
        user.setEmail("newuser@example.com");
        user.setCash(new BigDecimal("200.00"));

        userRepository.create(user);

        User createdUser = userRepository.getUserByUsername("newuser");
        assertNotNull(createdUser);
        assertEquals("newuser", createdUser.getUsername());
        assertEquals("newpassword", createdUser.getPassword());
        assertEquals("newuser@example.com", createdUser.getEmail());
        assertEquals(new BigDecimal("200.00"), createdUser.getCash());
    }

    @Test
    void getUserByUsername() {
        String username = "existingusername";

        User user = userRepository.getUserByUsername(username);

        assertNotNull(user);
        assertEquals(username, user.getUsername());
    }


    @Test
    void viewBalance() {
        User user = new User();
        user.setUsername("testuser");

        assertDoesNotThrow(() -> userRepository.viewBalance(user));
    }

    @Test
    void checkCash() {
        User user = new User();
        user.setUsername("testuser");

        BigDecimal initialBalance = userRepository.checkCash(user);

        assertEquals(new BigDecimal("100.00"), initialBalance);
    }

    @Test
    void getUserId() {
        User user = new User();
        user.setUsername("existingusername");

        int userId = userRepository.getUserId(user);

        assertEquals(1, userId);
    }

    @Test
    void deleteCash() {
        User user = new User();
        user.setUsername("testuser");
        BigDecimal initialBalance = new BigDecimal("50.00");

        int userId = userRepository.getUserId(user);
        userRepository.deleteCash(userId, initialBalance);

        assertEquals(new BigDecimal("50.00"), userRepository.checkCash(user));
    }

    @Test
    void addCashUser() {
        User user = new User();
        user.setUsername("testuser");
        BigDecimal addBalance  = BigDecimal.valueOf(130.00);

        int userId = userRepository.getUserId(user);
        userRepository.addCashUser(userId, addBalance);

        assertNull(userRepository.checkCash(user));
    }
}