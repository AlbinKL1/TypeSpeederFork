package se.ju23.typespeeder.Menu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Player.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu implements MenuService {

    private Scanner input;
    private final LoginService loginService;
    private final AccountCreationService accountCreationService;

    @Autowired
    public Menu(LoginService loginService,AccountCreationService accountCreationService) {
        this.loginService = loginService;
        this.accountCreationService = accountCreationService;
        this.input = new Scanner(System.in);
    }
    @Override
    public void loginMenu() {

        boolean continueLoop = true;

        do{
            System.out.println("""
                
                Welcome to TypeSpeeder
                
                1. Login.
                2. Create account.
                0. Exit program.
                """);

            System.out.print("Your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> login();
                case 2 -> createAccount();
                case 0 -> continueLoop = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }while(continueLoop);
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        loginService.login(username, password);
        displayMenu();
    }

    private void createAccount() {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter display name: ");
        String displayName = input.nextLine();
        accountCreationService.createAccount(username, password, displayName);
        loginMenu();
    }

    @Override
    public void displayMenu(){

        boolean continueLoop = true;

        do{
            System.out.println("""
                
                Player menu
                
                1. Play TypeSpeeder.
                2. Show ranking list.
                3. Update player information.
                4. Check player level and points.
                5. View patch notes.
                0. Logout.
                """);

            System.out.print("Your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> typeSpeeder();
                case 2 -> rankingList();
                case 3 -> editPlayer();
                case 4 -> viewPlayerLevelAndPoints();
                case 5 -> viewPatchNotes();
                case 0 -> continueLoop = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }while(continueLoop);
    }

    private void typeSpeeder() {
    }

    private void rankingList() {
        
    }

    private void editPlayer() {
    }

    private void viewPlayerLevelAndPoints() {
    }

    private void viewPatchNotes() {
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