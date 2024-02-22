package se.ju23.typespeeder.DatabaseAndUtility;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Entitys.*;

import java.time.LocalDateTime;
import java.util.List;



@Component
@Transactional
public class EntityManager {

    @Autowired
    private jakarta.persistence.EntityManager entityManager;
    @Autowired
    private PlayerRepo playerRepo;

    //Letters, Words, Symbols and Sentences.
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

    //Player
    public void createPlayer(String username, String password, String displayName) {
        Player player = new Player();
        player.setUsername(username);
        player.setPassword(password);
        player.setDisplayname(displayName);
        playerRepo.save(player);
    }
    public boolean playerExistsByUsername(String username) {
        return playerRepo.existsByUsername(username);
    }
    public Player getPlayerByUsernameAndPassword(String username, String password) {
        return playerRepo.findByUsernameAndPassword(username, password);
    }
    public void updateUsername(String oldUsername, String newUsername) {
        Player player = playerRepo.findByUsername(oldUsername);
        if (player != null) {
            player.setUsername(newUsername);
            playerRepo.save(player);
        }
    }
    public void updatePassword(String username, String newPassword) {
        Player player = playerRepo.findByUsername(username);
        if (player != null) {
            player.setPassword(newPassword);
            playerRepo.save(player);
        }
    }
    public void updateDisplayName(String username, String newDisplayName) {
        Player player = playerRepo.findByUsername(username);
        if (player != null) {
            player.setDisplayname(newDisplayName);
            playerRepo.save(player);
        }
    }
    public int getPlayerIdByUsername(String username) {
        Player player = playerRepo.findByUsername(username);
        return player != null ? player.getId() : 0;
    }
    public String getPlayerDisplayName(String username) {
        Player player = playerRepo.findByUsername(username);
        return player != null ? player.getDisplayname() : null;
    }

    //Points and Experience
    public void insertPoints(int playerId, int points) {
        Points pointsEntity = new Points();
        pointsEntity.setPlayerid(playerId);
        pointsEntity.setPoints(points);
        entityManager.persist(pointsEntity);
    }
    public void insertExperience(int playerId, int experience) {
        Experience experienceEntity = new Experience();
        experienceEntity.setPlayerid(playerId);
        experienceEntity.setExperience(experience);
        entityManager.persist(experienceEntity);
    }
    public void insertPointsAndExperience(int playerId, int points) {
        insertPoints(playerId, points);
        int experience = (int) (points * 0.1);
        insertExperience(playerId, experience);
    }
    public List<Object[]> getRankingList() {
        String queryStr = "SELECT p.displayname, SUM(pts.points) AS total_points " +
                "FROM Player p " +
                "JOIN Points pts ON p.id = pts.playerid " +
                "GROUP BY p.id " +
                "ORDER BY total_points DESC";
        Query query = entityManager.createQuery(queryStr);
        return query.getResultList();
    }
    public int getTotalPoints(int playerId) {
        String queryStr = "SELECT SUM(pts.points) FROM Points pts WHERE pts.playerid = :playerId";
        TypedQuery<Long> query = entityManager.createQuery(queryStr, Long.class);
        query.setParameter("playerId", playerId);
        Long result = query.getSingleResult();
        return result != null ? result.intValue() : 0;
    }
    public int getPlayerLevel(int playerId) {
        String queryStr = "SELECT SUM(exp.experience) FROM Experience exp WHERE exp.playerid = :playerId";
        TypedQuery<Long> query = entityManager.createQuery(queryStr, Long.class);
        query.setParameter("playerId", playerId);
        Long totalExperience = query.getSingleResult();
        return totalExperience != null ? totalExperience.intValue() / 100 : 0;
    }

    //Patch and Newsletter
    public void createPatchNote(String patchVersion, LocalDateTime releaseDateTime, String notes) {
        Patch patch = new Patch();
        patch.setPatchversion(patchVersion);
        patch.setReleasedatetime(releaseDateTime);
        patch.setNotes(notes);
        entityManager.persist(patch);
    }
    public List<Patch> getAllPatchNotes() {
        Query query = entityManager.createQuery("SELECT p FROM Patch p");
        return query.getResultList();
    }
    public void createNewsLetter(LocalDateTime publishDateTime, String content) {
        Newsletter newsLetter = new Newsletter();
        newsLetter.setPublishdatetime(publishDateTime);
        newsLetter.setContent(content != null ? content : "");
        entityManager.persist(newsLetter);
    }
    public List<Newsletter> getAllNewsletters() {
        Query query = entityManager.createQuery("SELECT n FROM Newsletter n");
        return query.getResultList();
    }
}