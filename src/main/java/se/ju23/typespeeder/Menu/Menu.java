package se.ju23.typespeeder.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu implements MenuService {
    private Scanner input;

    public Menu() {
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
            case 1:
                login();
                break;
            case 2:
                createAccount();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void login() {
        System.out.println("Login");
        selectLanguage();
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