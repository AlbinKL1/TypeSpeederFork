package se.ju23.typespeeder.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int playerid;
    private int points;

    // Getters
    public int getId() {
        return id;
    }
    public int getPlayerid() {
        return playerid;
    }
    public int getPoints() {
        return points;
    }

    //Setters
    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }
    public void setPoints(int points) {
        this.points = points;
    }

}
