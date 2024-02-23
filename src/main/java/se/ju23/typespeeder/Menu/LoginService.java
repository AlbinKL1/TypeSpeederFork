package se.ju23.typespeeder.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.DatabaseAndUtility.UtilityService;
import se.ju23.typespeeder.Entitys.Player;
import se.ju23.typespeeder.Entitys.PlayerRepo;

@Service
public class LoginService {
    private PlayerRepo playerRepo;
    @Autowired
    public LoginService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    //Login method.
    public boolean login(String username, String password) {
        Player player = playerRepo.findByUsernameAndPassword(username, password);
        if (player != null) {
            System.out.println("\nLogin successful. Welcome, " + player.getDisplayName() + "!");
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
        int playerType = 1;
        if (playerRepo.existsByUsername(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }
        Player player = new Player(username,password,displayName,playerType);
        playerRepo.save(player);
        System.out.println("Account created successfully!");
    }

    //Edit user methods-
    public void editUsername(String username) {
        UtilityService.input.nextLine();
        System.out.print("Enter new username: ");
        String newUsername = UtilityService.input.nextLine();
        Player player = playerRepo.findByUsername(username);
        if (player != null) {
            player.setUsername(newUsername);
            playerRepo.save(player);
        }
        System.out.println("Username updated successfully!");
    }
    public void editPassword(String username) {
        UtilityService.input.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = UtilityService.input.nextLine();
        Player player = playerRepo.findByUsername(username);
        if (player != null) {
            player.setPassword(newPassword);
            playerRepo.save(player);
        }
        System.out.println("Password updated successfully!");
    }
    public void editDisplayName(String username) {
        UtilityService.input.nextLine();
        System.out.print("Enter new display name: ");
        String newDisplayName = UtilityService.input.nextLine();
        Player player = playerRepo.findByUsername(username);
        if (player != null) {
            player.setDisplayName(newDisplayName);
            playerRepo.save(player);
        }
        System.out.println("Display name updated successfully!");
    }
}
