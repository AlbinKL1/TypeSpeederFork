package se.ju23.typespeeder.Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
@Transactional
public class DatabaseManager {

    @Autowired
    private EntityManager entityManager;


    public List<String> getEnglishLetters() {
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

    public List<String> getSwedishLetters() {
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
        return ((Number) results.get(0)).intValue() > 0;
    }

    public String getPlayerByUsernameAndPassword(String username, String password) {
        String queryStr = "SELECT * FROM Player WHERE username = ? AND password = ?";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, username);
        query.setParameter(2, password);
        List<Object[]> results = query.getResultList();
        if (!results.isEmpty()) {
            Object[] result = results.get(0);
            return (String) result[3];
        }
        return null;
    }

    public void updateUsername(String oldUsername, String newUsername) {
        String queryStr = "UPDATE Player SET username = ? WHERE username = ?";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, newUsername);
        query.setParameter(2, oldUsername);
        query.executeUpdate();
    }


    public void updatePassword(String username, String newPassword) {
        String queryStr = "UPDATE Player SET password = ? WHERE username = ?";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, newPassword);
        query.setParameter(2, username);
        query.executeUpdate();
    }

    public void updateDisplayName(String username, String newDisplayName) {
        String queryStr = "UPDATE Player SET displayname = ? WHERE username = ?";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, newDisplayName);
        query.setParameter(2, username);
        query.executeUpdate();
    }
    public int getPlayerIdByUsername(String username) {
        String queryStr = "SELECT id FROM player WHERE username = ?";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, username);
        return (int) query.getSingleResult();
    }
    public void insertPoints(int playerId, int points) {
        String queryStr = "INSERT INTO points (playerid, points) VALUES (?, ?)";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, playerId);
        query.setParameter(2, points);
        query.executeUpdate();
    }
    public void insertExperience(int playerId, int experience) {
        String queryStr = "INSERT INTO experience (playerid, experience) VALUES (?, ?)";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, playerId);
        query.setParameter(2, experience);
        query.executeUpdate();
    }

    public void insertPointsAndExperience(int playerId, int points) {
        insertPoints(playerId, points);

        int experience = (int) (points * 0.1);

        insertExperience(playerId, experience);
    }
    public List<Object[]> getRankingList() {
        String queryStr = "SELECT displayname, SUM(points) AS total_points FROM Player JOIN points ON Player.id = points.playerid GROUP BY Player.id ORDER BY total_points DESC";
        Query query = entityManager.createNativeQuery(queryStr);
        return query.getResultList();
    }
    public int getTotalPoints(int playerId) {
        String queryStr = "SELECT SUM(points) FROM points WHERE playerid = ?";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, playerId);
        return ((Number) query.getSingleResult()).intValue();
    }
    public int getPlayerLevel(int playerId) {
        String queryStr = "SELECT SUM(experience) FROM experience WHERE playerid = ?";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter(1, playerId);
        int totalExperience = ((Number) query.getSingleResult()).intValue();
        return totalExperience / 100; // Assuming every 100 experience points corresponds to one level
    }
}