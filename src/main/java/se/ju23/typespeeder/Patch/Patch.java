package se.ju23.typespeeder.Patch;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Patch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String patchversion;
    public LocalDateTime releasedatetime;
    public String notes;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getPatchversion() {
        return patchversion;
    }

    public void setPatchversion(String patchVersion) {
        this.patchversion = patchVersion;
    }

    public LocalDateTime getReleasedatetime() {
        return releasedatetime;
    }

    public void setReleasedatetime(LocalDateTime releaseDateTime) {
        this.releasedatetime = releaseDateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
