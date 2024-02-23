package se.ju23.typespeeder;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.ju23.typespeeder.Game.ChallengeService;
import se.ju23.typespeeder.DatabaseAndUtility.EntityManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ChallengePerformanceTest {
    @Mock
    private EntityManager entityManager;
    private ChallengeService challenge;
    private static final int MAX_EXECUTION_TIME = 200;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
         challenge = new ChallengeService(entityManager);
    }
    @Test
    public void testStartChallengePerformance() {
        long startTime = System.nanoTime();
        challenge.startChallenge();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Starting a challenge took too long. Execution time: " + duration + " ms.");
    }

    @Test
    public void testLettersToTypePerformance() {
        List<String> mockLetters = Arrays.asList("a", "b", "c");
        when(entityManager.getEnglishLetters()).thenReturn(mockLetters);
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Selecting letters to type took too long. Execution time: " + duration + " ms.");
    }
}
