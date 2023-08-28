package org.example.controllers;

import org.example.models.User;
import java.math.BigDecimal;
import java.util.Scanner;

public final class GlobalController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserController userController = new UserController();
    private static final AccountController accountController = new AccountController();
    private static final TransactionController transactionController = new TransactionController();
    private static final BankController bankController = new BankController();


    public static void startConsoleApp() {
        while (true) {
            System.out.println("==CleverBank==");

            System.out.println("""
                    1 - registration\s
                    2 - login\s
                    0 - exit\s
                    """);
            String step = scanner.nextLine();

            switch (step) {
                case "1" -> registerUser();
                case "2" -> loginUser();
                case "0" -> {
                    System.out.println("Exiting the application.");
                    return;
                }
                default -> System.out.println("Incorrect input. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.println("=====Registration=====");
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Print your cash");
        BigDecimal cash = BigDecimal.valueOf(scanner.nextInt());
        scanner.nextLine();
        User user = new User(username, password, email, cash);
        userController.create(user);
    }

    private static void loginUser() {
        System.out.println("=====Login=====");
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        User user = userController.getUserByUsername(username);

        if (user == null) {
            System.out.println("User with such username not found.");
            return;
        }

        if (userController.checkPassword(user, password)) {
            System.out.println("Welcome, " + user.getUsername() + "!");
        } else {
            System.out.println("Invalid password. Please try again.");
        }
    }

}