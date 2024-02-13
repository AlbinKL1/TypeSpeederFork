package se.ju23.typespeeder.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.Player.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    Player findByUsernameAndPassword(String username, String password);
}
