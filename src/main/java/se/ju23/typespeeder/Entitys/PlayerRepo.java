package se.ju23.typespeeder.Entitys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo  extends JpaRepository <Player,Integer>{
    //Methods to finding player related stuff in the database.
    Player findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    Player findByUsername(String username);
}
