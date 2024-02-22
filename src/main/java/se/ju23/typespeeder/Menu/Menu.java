package se.ju23.typespeeder.Menu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.DatabaseAndUtility.EntityManager;
import se.ju23.typespeeder.DatabaseAndUtility.UtilityService;
import se.ju23.typespeeder.Game.ChallengeService;
import se.ju23.typespeeder.Game.DisplayService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Menu implements MenuService {

    private final LoginService loginService;
    private final ChallengeService challenge;
    private final DisplayService display;

    @Autowired
    public Menu(LoginService loginService, ChallengeService challenge, DisplayService display) {
        this.loginService = loginService;
        this.challenge = challenge;
        this.display = display;
    }

    //Login menu.
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
            int choice = UtilityService.getIntInput();

            switch (choice) {
                case 1 -> {
                    String username = login();
                    if (username != null) {
                        UtilityService.loggedInUsername = username;
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
        UtilityService.input.nextLine();
        System.out.print("Enter username: ");
        String username = UtilityService.input.nextLine();
        System.out.print("Enter password: ");
        String password = UtilityService.input.nextLine();
        if (loginService.login(username, password)) {
            return username;
        } else {
            System.out.println("Login failed. Please try again.");
            return null;
        }
    }

    //Create account menu.
    private void createAccount() {
        loginService.createAccount();
        loginMenu();
    }

    //Main menu.
    @Override
    public void displayMenu() {
        boolean continueLoop = true;

        System.out.println("\nSelect language:");
        System.out.println("1. English");
        System.out.println("2. Svenska");

        System.out.print("Your choice: ");
        int languageChoice = UtilityService.getIntInput();

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
            System.out.println("\nPlayer menu\n");

            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.println("0. Logout.");

            System.out.print("Your choice: ");
            int choice = UtilityService.getIntInput();

            switch (choice) {
                case 1 -> challenge.startChallenge();
                case 2 -> rankingList();
                case 3 -> editPlayer();
                case 4 -> viewPlayerLevelAndPoints();
                case 5 -> writePatchNotes();
                case 6 -> viewPatchNotes();
                case 7 -> writeNewsLetter();
                case 8 -> viewNewsLetter();
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
        options.add("Write a newsletter");
        options.add("View newsletters");
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
        options.add("Skriv nyhetsbrev");
        options.add("Visa nyhetsbrev");
        return options;
    }

    //RankingList.
    private void rankingList() {
        display.showRankingList();
    }

    //Edit player menu and methods..
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
            int choice = UtilityService.getIntInput();

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
        loginService.editUsername(UtilityService.loggedInUsername);
    }
    private void editPassword() {
        loginService.editPassword(UtilityService.loggedInUsername);
    }
    private void editDisplayName() {
        loginService.editDisplayName(UtilityService.loggedInUsername);
    }

    //Points and levels.
    private void viewPlayerLevelAndPoints() {
        display.viewPlayerLevelAndPoints(UtilityService.loggedInUsername);
    }

    //Patch note creation and viewing.
    public void writePatchNotes() {
        display.writePatchNotes();
    }
    public void viewPatchNotes() {
        display.viewPatchNotes();
    }

    //Newsletter creation and viewing.
    public void writeNewsLetter(){
        display.writeNewsLetter();

    }
    public void viewNewsLetter(){
       display.viewNewsLetter();
    }
}