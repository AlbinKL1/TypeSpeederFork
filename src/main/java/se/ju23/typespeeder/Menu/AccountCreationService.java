package se.ju23.typespeeder.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.Player.Player;
import se.ju23.typespeeder.Player.PlayerRepo;

@Service
public class AccountCreationService {
    private final PlayerRepo playerRepo;

    @Autowired
    public AccountCreationService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public void createAccount(String username, String password, String displayName) {
        Player newPlayer = new Player(username, password, displayName);
        playerRepo.save(newPlayer);
        System.out.println("Account created successfully!");
    }
}
