package se.ju23.typespeeder.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    Player findByUsernameAndPassword(String username, String password);
}
