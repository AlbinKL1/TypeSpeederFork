package se.ju23.typespeeder.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String displayName;
    private int playerType;

    //Constructors
    public Player(String username, String password, String displayName,int playerType) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.playerType = playerType;
    }
    public Player() {

    }

    // Getters
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getDisplayName() {
        return displayName;
    }
    public int getPlayerType() {
        return playerType;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }
}
