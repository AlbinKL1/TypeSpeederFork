package se.ju23.typespeeder.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Database.DatabaseManager;

import java.util.List;

@Component
public class Display {
    private DatabaseManager databaseManager;
    @Autowired
    public Display(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void showRankingList() {
        List<Object[]> rankingList = databaseManager.getRankingList();
        System.out.println("Ranking List:");
        for (int i = 0; i < rankingList.size(); i++) {
            Object[] playerInfo = rankingList.get(i);
            String playerName = (String) playerInfo[0];
            int totalPoints = ((Number) playerInfo[1]).intValue();
            System.out.println((i + 1) + ". " + playerName + ": " + totalPoints + " points");
        }
    }
    public void viewPlayerLevelAndPoints(String loggedInUsername) {
        int playerId = databaseManager.getPlayerIdByUsername(loggedInUsername);
        int playerLevel = databaseManager.getPlayerLevel(playerId);
        int totalPoints = databaseManager.getTotalPoints(playerId);
        System.out.println("Player Level: " + playerLevel);
        System.out.println("Total Points: " + totalPoints);
    }
}
