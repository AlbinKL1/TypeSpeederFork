package se.ju23.typespeeder.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.DatabaseAndUtility.EntityManager;
import se.ju23.typespeeder.DatabaseAndUtility.UtilityService;
import se.ju23.typespeeder.Entitys.Player;

@Service
public class LoginService {
    private final EntityManager databaseManager;

    @Autowired
    public LoginService(EntityManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    //Login method.
    public boolean login(String username, String password) {
        Player player = databaseManager.getPlayerByUsernameAndPassword(username, password);
        if (player != null) {
            System.out.println("\nLogin successful. Welcome, " + player.getDisplayname() + "!");
            UtilityService.setLoggedInUsername(username);
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    //Account creation method.
    public void createAccount() {
        UtilityService.input.nextLine();
        System.out.print("Enter username: ");
        String username = UtilityService.input.nextLine();
        System.out.print("Enter password: ");
        String password = UtilityService.input.nextLine();
        System.out.print("Enter display name: ");
        String displayName = UtilityService.input.nextLine();
        if (databaseManager.playerExistsByUsername(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }
        databaseManager.createPlayer(username, password, displayName);
        System.out.println("Account created successfully!");
    }

    //Edit user methods-
    public void editUsername(String username) {
        UtilityService.input.nextLine();
        System.out.print("Enter new username: ");
        String newUsername = UtilityService.input.nextLine();
        databaseManager.updateUsername(username, newUsername);
        System.out.println("Username updated successfully!");
    }
    public void editPassword(String username) {
        UtilityService.input.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = UtilityService.input.nextLine();
        databaseManager.updatePassword(username, newPassword);
        System.out.println("Password updated successfully!");
    }
    public void editDisplayName(String username) {
        UtilityService.input.nextLine();
        System.out.print("Enter new display name: ");
        String newDisplayName = UtilityService.input.nextLine();
        databaseManager.updateDisplayName(username, newDisplayName);
        System.out.println("Display name updated successfully!");
    }
}
