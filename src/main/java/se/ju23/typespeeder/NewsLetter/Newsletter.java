package se.ju23.typespeeder.NewsLetter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Newsletter {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
     private int id;
    private static int MIN_CONTENT_LENGTH = 100;
    private static int MAX_CONTENT_LENGTH = 200;
    public LocalDateTime publishdatetime;

    public String content;

    public int getId() {
        return id;
    }

    public LocalDateTime getPublishdatetime() {
        return publishdatetime;
    }

    public String getContent() {
        return content;
    }

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
