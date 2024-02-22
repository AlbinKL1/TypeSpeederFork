package se.ju23.typespeeder.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private static int MIN_CONTENT_LENGTH = 100;
    private static int MAX_CONTENT_LENGTH = 200;

    public LocalDateTime publishdatetime;
    public String content;

    // Constructor
    public Newsletter() {
        this.publishdatetime = LocalDateTime.now();
    }

    // Getters
    public int getId() {
        return id;
    }
    public LocalDateTime getPublishdatetime() {
        return publishdatetime;
    }
    public String getContent() {
        return content;
    }

    // Setters
    public void setPublishdatetime(LocalDateTime publishdatetime) {
        this.publishdatetime = publishdatetime;
    }
    public void setContent(String content) {
        if (content == null || content.length() < MIN_CONTENT_LENGTH || content.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException("Content must be between 100 and 200 characters long.");
        }
        this.content = content;
    }
}
