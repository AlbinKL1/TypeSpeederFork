package se.ju23.typespeeder.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.DatabaseAndUtility.EntityManager;
import se.ju23.typespeeder.DatabaseAndUtility.UtilityService;
import se.ju23.typespeeder.Entitys.Newsletter;
import se.ju23.typespeeder.Entitys.Patch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DisplayService {
    private EntityManager databaseManager;
    @Autowired
    public DisplayService(EntityManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    //Points and level showcase.
    public void showRankingList() {
        List<Object[]> rankingList = databaseManager.getRankingList();
        System.out.println("\nRanking List\n");
        for (int i = 0; i < rankingList.size(); i++) {
            Object[] playerInfo = rankingList.get(i);
            String playerName = (String) playerInfo[0];
            int totalPoints = ((Number) playerInfo[1]).intValue();
            System.out.println((i + 1) + ". " + playerName + ": " + totalPoints + " points");
        }
    }
    public void viewPlayerLevelAndPoints(String loggedInUsername) {
        String playerName = databaseManager.getPlayerDisplayName(loggedInUsername);
        int playerId = databaseManager.getPlayerIdByUsername(loggedInUsername);
        int playerLevel = databaseManager.getPlayerLevel(playerId);
        int totalPoints = databaseManager.getTotalPoints(playerId);
        System.out.println("\nPlayer: " + playerName);
        System.out.println("Player Level: " + playerLevel);
        System.out.println("Total Points: " + totalPoints);
    }

    //Patch note creation and viewing.
    public void writePatchNotes() {
        UtilityService.input.nextLine();

        System.out.print("\nEnter patch version (0.0.0): ");
        String patchVersion = UtilityService.input.nextLine();

        System.out.print("Enter patch notes: ");
        String notes = UtilityService.input.nextLine();

        LocalDateTime releaseDateTime = LocalDateTime.now();
        System.out.println("Current date and time: " + releaseDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        databaseManager.createPatchNote(patchVersion, releaseDateTime, notes);
        System.out.println("Patch notes created successfully.");

    }
    public void viewPatchNotes() {
        List<Patch> patchNotes = databaseManager.getAllPatchNotes();
        if (patchNotes.isEmpty()) {
            System.out.println("\nNo patch notes available.");
        } else {
            System.out.println("\nPatch Notes\n");
            for (Patch patchNote : patchNotes) {
                System.out.println("Version: " + patchNote.getPatchversion());
                System.out.println("Release Date: " + patchNote.getReleasedatetime());
                System.out.println("Notes: " + patchNote.getNotes());
                System.out.println();
            }
        }
    }

    //Newsletter creation and viewing.
    public void writeNewsLetter(){
        UtilityService.input.nextLine();

        LocalDateTime publishDateTime = LocalDateTime.now();
        System.out.println("Current date and time: " + publishDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.print("Enter newsletter content: ");
        String content = UtilityService.input.nextLine();

        databaseManager.createNewsLetter(publishDateTime, content);
        System.out.println("Patch notes created successfully.");

    }
    public void viewNewsLetter(){
        List<Newsletter> newsLetters = databaseManager.getAllNewsletters();
        if (newsLetters.isEmpty()) {
            System.out.println("\nNo newsletter available.");
        } else {
            System.out.println("\nNewsletters\n");
            for (Newsletter newsLetter : newsLetters) {
                System.out.println("Publish Date: " + newsLetter.getPublishdatetime());
                System.out.println("Content: " + newsLetter.getContent());
                System.out.println();
            }
        }
    }
}
