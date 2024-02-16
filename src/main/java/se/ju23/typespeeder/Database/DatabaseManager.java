package se.ju23.typespeeder.Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseManager {

    @Autowired
    private EntityManager entityManager;


    public List<String> getEnglishLetters () {
        return fetchData("SELECT letter FROM letters WHERE languageid = 1");
    }
    public List<String> getEnglishWords() {
        return fetchData("SELECT word FROM words WHERE languageid = 1");
    }

    public List<String> getEnglishSymbols() {
        return fetchData("SELECT symbol FROM Symbols WHERE languageid = 1");
    }

    public List<String> getEnglishSentences() {
        return fetchData("SELECT sentence FROM sentences WHERE languageid = 1");
    }
    public List<String> getSwedishLetters () {
        return fetchData("SELECT letter FROM letters WHERE languageid = 2");
    }

    public List<String> getSwedishWords() {
        return fetchData("SELECT word FROM words WHERE languageid = 2");
    }

    public List<String> getSwedishSymbols() {
        return fetchData("SELECT symbol FROM Symbols WHERE languageid = 2");
    }

    public List<String> getSwedishSentences() {
        return fetchData("SELECT sentence FROM Sentences WHERE languageid = 2");
    }

    private List<String> fetchData(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query);
        return nativeQuery.getResultList();
    }
    public void createPlayer(String username, String password, String displayName) {
        String queryStr = "INSERT INTO Player (username, password, display_name) VALUES (?, ?, ?)";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, username);
        query.setParameter(2, password);
        query.setParameter(3, displayName);
        query.executeUpdate();
    }

    public boolean playerExistsByUsername(String username) {
        String queryStr = "SELECT COUNT(*) FROM Player WHERE username = ?";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, username);
        List<?> results = query.getResultList();
        return ((Number)results.get(0)).intValue() > 0;
    }

    public String getPlayerByUsernameAndPassword(String username, String password) {
        String queryStr = "SELECT * FROM Player WHERE username = ? AND password = ?";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, username);
        query.setParameter(2, password);
        List<Object[]> results = query.getResultList();
        if (!results.isEmpty()) {
            Object[] result = results.get(0);
            return (String) result[3]; // Returning displayName
        }
        return null;
    }

}