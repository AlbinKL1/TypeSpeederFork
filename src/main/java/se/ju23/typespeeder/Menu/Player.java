package se.ju23.typespeeder.Menu;

import jakarta.persistence.*;

@Entity
@Table(name="player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String displayname;

    public Player(String loginName, String password, String displayName) {
        this.username = loginName;
        this.password = password;
        this.displayname = displayName;
    }

    public Player() {

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setUsername(String loginName) {
        this.username = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDisplayname(String displayName) {
        this.displayname = displayName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "LoginName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", DisplayName='" + displayname + '\'' +
                '}';
    }
}
