package se.ju23.typespeeder.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.Database.DatabaseManager;

@Service
public class LoginService {
    private final DatabaseManager databaseManager;

    @Autowired
    public LoginService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public boolean login(String username, String password) {
        String displayName = databaseManager.getPlayerByUsernameAndPassword(username, password);
        if (displayName != null) {
            System.out.println("Login successful. Welcome, " + displayName + "!");
            Menu.setLoggedInUsername(username); // Set logged-in user using static method
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    public void createAccount(String username, String password, String displayName) {
        if (databaseManager.playerExistsByUsername(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }
        databaseManager.createPlayer(username, password, displayName);
        System.out.println("Account created successfully!");
    }
    public void editUsername(String username, String newUsername) {
        databaseManager.updateUsername(username, newUsername);
        System.out.println("Username updated successfully!");
    }

    public void editPassword(String username, String newPassword) {
        databaseManager.updatePassword(username, newPassword);
        System.out.println("Password updated successfully!");
    }

    public void editDisplayName(String username, String newDisplayName) {
        databaseManager.updateDisplayName(username, newDisplayName);
        System.out.println("Display name updated successfully!");
    }
}
