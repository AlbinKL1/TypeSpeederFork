package se.ju23.typespeeder.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Patch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String patchVersion;
    public LocalDateTime releaseDateTime;
    public String notes;

    //Constructors
    public Patch(String patchVersion, LocalDateTime releaseDateTime, String notes) {
        this.patchVersion = patchVersion;
        this.releaseDateTime = releaseDateTime;
        this.notes = notes;
    }
    public Patch() {

    }

    // Getters
    public int getId() {
        return id;
    }
    public String getPatchVersion() {
        return patchVersion;
    }
    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }
    public LocalDateTime getReleaseDateTime() {
        return releaseDateTime;
    }

    //Setters
    public void setReleaseDateTime(LocalDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
