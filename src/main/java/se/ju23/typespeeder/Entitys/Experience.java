package se.ju23.typespeeder.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private int playerid;
    private int experience;

    //Getters
    public int getId() {
        return id;
    }
    public int getPlayerid() {
        return playerid;
    }
    public int getExperience() {
        return experience;
    }

    //Setters
    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
}
