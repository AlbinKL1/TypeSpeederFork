package se.ju23.typespeeder.Menu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Database.DatabaseManager;
import se.ju23.typespeeder.Game.Challenge;
import se.ju23.typespeeder.Game.Display;
import se.ju23.typespeeder.Patch.Patch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu implements MenuService {


    @Autowired
    private DatabaseManager databaseManager;
    private Scanner input;
    private final LoginService loginService;
    private final Challenge challenge;
    private final Display display;
    private  static String loggedInUsername;
    public static String getLoggedInUsername() {
        return loggedInUsername;
    }

    public static void setLoggedInUsername(String loggedInUsername) {
        Menu.loggedInUsername = loggedInUsername;
    }

    @Autowired
    public Menu(LoginService loginService, Challenge challenge, Display display) {
        this.loginService = loginService;
        this.challenge = challenge;
        this.display = display;
        this.input = new Scanner(System.in);
    }

    @Override
    public void loginMenu() {

        boolean continueLoop = true;
        long startTime = System.nanoTime();

        do {
            System.out.println("""
                                
                    Welcome to TypeSpeeder
                                    
                    1. Login.
                    2. Create account.
                    0. Exit program.
                    """);

            System.out.print("Your choice: ");
            int choice = 0;
            if(input.hasNextInt())
            {
                choice = input.nextInt();
            }

            switch (choice) {
                case 1 -> {
                    String username = login();
                    if (username != null) {
                        loggedInUsername = username;
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime) / 1000000;
                        System.out.println("Login and menu loaded in " + duration + " milliseconds.");
                        displayMenu(
                        );
                    }
                }
                case 2 -> createAccount();
                case 0 -> continueLoop = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (continueLoop);
    }

    private String login() {
        input.nextLine();
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        if (loginService.login(username, password)) {
            return username;
        } else {
            System.out.println("Login failed. Please try again.");
            return null;
        }
    }

    private void createAccount() {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter display name: ");
        String displayName = input.nextLine();
        loginService.createAccount(username, password, displayName);
        loginMenu();
    }

    @Override
    public void displayMenu() {
        boolean continueLoop = true;

        System.out.println("\nSelect language:");
        System.out.println("1. English");
        System.out.println("2. Svenska");
        System.out.print("Your choice: ");
        int languageChoice = 0;
        if(input.hasNextInt())
        {
            languageChoice = input.nextInt();
        }

        List<String> options;
        switch (languageChoice) {
            case 1 -> {
                options = getEnglishMenuOptions();
                System.out.println("English chosen.\n");
            }
            case 2 -> {
                options = getSwedishMenuOptions();
                System.out.println("Svenska valt.\n");
            }
            default -> {
                System.out.println("Invalid choice. Defaulting to English.\n");
                options = getEnglishMenuOptions();
            }
        }

        do {
            System.out.println("Player menu\n");

            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.print("Your choice: ");
            int choice = 0;
            if(input.hasNextInt())
            {
                choice = input.nextInt();
            }

            switch (choice) {
                case 1 -> challenge.startChallenge();
                case 2 -> rankingList();
                case 3 -> editPlayer();
                case 4 -> viewPlayerLevelAndPoints();
                case 5 -> writePatchNotes();
                case 6 -> viewPatchNotes();
                case 0 -> continueLoop = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (continueLoop);
    }

    @Override
    public List<String> getEnglishMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add("Play TypeSpeeder.");
        options.add("Show ranking list.");
        options.add("Update player information.");
        options.add("Check player level and points.");
        options.add("Write patch notes.");
        options.add("View patch notes.");
        options.add("Logout.");
        return options;
    }

    @Override
    public List<String> getSwedishMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add("Spela TypeSpeeder.");
        options.add("Visa rankinglista.");
        options.add("Uppdatera spelarinformation.");
        options.add("Kolla spelarnivå och poäng.");
        options.add("Skriv patchanteckningar.");
        options.add("Visa patchanteckningar.");
        options.add("Logga ut.");
        return options;
    }

    private void rankingList() {
        display.showRankingList();
    }

    private void editPlayer() {

        boolean continueLoop = true;

        do{
            System.out.println("""
                
                Edit Player menu
                
                1. Edit username.
                2. Edit password.
                3. Edit display name.
                0. back.
                """);

            System.out.print("Your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> editUsername();
                case 2 -> editPassword();
                case 3 -> editDisplayName();
                case 0 -> continueLoop = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }while(continueLoop);

    }

    private void editUsername() {
        System.out.print("Enter new username: ");
        String newUsername = input.nextLine();
        loginService.editUsername(loggedInUsername, newUsername);
    }

    private void editPassword() {
        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();
        loginService.editPassword(loggedInUsername, newPassword);
    }

    private void editDisplayName() {
        System.out.print("Enter new display name: ");
        String newDisplayName = input.nextLine();
        loginService.editDisplayName(loggedInUsername, newDisplayName);
    }

    private void viewPlayerLevelAndPoints() {
        display.viewPlayerLevelAndPoints(loggedInUsername);
    }

    public void writePatchNotes() {
        input.nextLine();

        System.out.print("\nEnter patch version (0.0.0): ");
        String patchVersion = input.nextLine();

        System.out.print("Enter patch notes: ");
        String notes = input.nextLine();

        LocalDateTime releaseDateTime = LocalDateTime.now();
        System.out.println("Current date and time: " + releaseDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        databaseManager.createPatchNote(patchVersion, releaseDateTime, notes);
        System.out.println("Patch notes created successfully.");

    }

    public void viewPatchNotes() {
        List<Patch> patchNotes = databaseManager.getAllPatchNotes();
        if (patchNotes.isEmpty()) {
            System.out.println("\nNo patch notes available.");
        } else {
            System.out.println("\nPatch Notes\n");
            for (Patch patchNote : patchNotes) {
                System.out.println("Version: " + patchNote.getPatchversion());
                System.out.println("Release Date: " + patchNote.getReleasedatetime());
                System.out.println("Notes: " + patchNote.getNotes());
                System.out.println();
            }
        }
    }


    @Override
    public List<String> getMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add("Play TypeSpeeder.");
        options.add("Show ranking list.");
        options.add("Update player information.");
        options.add("Check player level and points.");
        options.add("View patch notes.");
        return options;
    }
}