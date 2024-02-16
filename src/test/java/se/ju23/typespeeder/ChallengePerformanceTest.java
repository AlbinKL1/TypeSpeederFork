package se.ju23.typespeeder;


import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.Challenge.Challenge;
import se.ju23.typespeeder.Database.DatabaseManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChallengePerformanceTest {
    private static final int MAX_EXECUTION_TIME = 200;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    private  DatabaseManager databaseManager;
    @Test
    public void testStartChallengePerformance() {
        Challenge challenge = new Challenge(databaseManager);
        long startTime = System.nanoTime();
        challenge.startChallenge();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Starting a challenge took too long. Execution time: " + duration + " ms.");
    }
    @Test
    public void testLettersToTypePerformance(List<String> letters) {
        Challenge challenge = new Challenge(databaseManager);
        long startTime = System.nanoTime();
        challenge.lettersToType(letters);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Selecting letters to type took too long. Execution time: " + duration + " ms.");
    }
}
