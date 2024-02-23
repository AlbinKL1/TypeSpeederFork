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
    private int playerId;
    private int points;

    // Getters
    public int getId() {
        return id;
    }
    public int getPlayerId() {
        return playerId;
    }
    public int getPoints() {
        return points;
    }

    //Setters
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public void setPoints(int points) {
        this.points = points;
    }

}
