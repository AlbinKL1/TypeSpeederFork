package se.ju23.typespeeder.Menu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu implements MenuService {

    private Scanner input;
    private final PlayerRepo playerRepo;

    @Autowired
    public Menu(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
        this.input = new Scanner(System.in);
    }
    @Override
    public void displayMenu() {
        System.out.println("Welcome to TypeSpeeder!");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1 ->
                login();
            case 2 ->
                createAccount();
            case 3 -> {
                System.out.println("Exiting...");
                System.exit(0);
            }
            default ->
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        Player player = playerRepo.findByUsernameAndPassword(username, password);
        if (player != null) {
            System.out.println("Login successful. Welcome, " + player.getDisplayname() + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void createAccount() {
        System.out.println("Create Account");
        selectLanguage();
    }

    private void selectLanguage() {
        System.out.println("Select Language:");
        System.out.println("1. English");
        System.out.println("2. Svenska");
    }

    @Override
    public List<String> getMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add("Login");
        options.add("Create Account");
        options.add("Exit");
        return options;
    }
}