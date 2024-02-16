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

    public void login(String username, String password) {
        String displayName = databaseManager.getPlayerByUsernameAndPassword(username, password);
        if (displayName != null) {
            System.out.println("Login successful. Welcome, " + displayName + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
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
}
