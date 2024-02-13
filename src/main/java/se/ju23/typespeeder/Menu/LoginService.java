package se.ju23.typespeeder.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.Player.Player;
import se.ju23.typespeeder.Player.PlayerRepo;

@Service
public class LoginService {
    private final PlayerRepo playerRepo;

    @Autowired
    public LoginService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public void login(String username, String password) {
        Player player = playerRepo.findByUsernameAndPassword(username, password);
        if (player != null) {
            System.out.println("Login successful. Welcome, " + player.getDisplayname() + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
}
