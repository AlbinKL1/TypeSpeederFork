package se.ju23.typespeeder;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengeTest {
    @Test
    public void testChallengeClassExists() {
        try {
            Class<?> challengeClass = Class.forName("se.ju23.typespeeder.Game.ChallengeService");
        } catch (ClassNotFoundException e) {
            fail("Challenge class could not be found.");
        }
    }
    @Test
    public void testLettersToTypeMethodExists() {
        try {
            Class<?> challengeClass = Class.forName("se.ju23.typespeeder.Game.ChallengeService");
            Method method = challengeClass.getMethod("lettersToType",List.class);
            assertNotNull(method, "The method 'lettersToType' should exist in the Challenge class.");
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            fail("The method 'lettersToType' could not be found in the Challenge class.");
        }
    }
    @Test
    public void testStartChallengeMethodExists() {
        try {
            Class<?> challengeClass = Class.forName("se.ju23.typespeeder.Game.ChallengeService");
            Method method = challengeClass.getMethod("startChallenge");
            assertNotNull(method, "The method 'startChallenge' should exist in the Challenge class.");
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            fail("The method 'startChallenge' could not be found in the Challenge class.");
        }
    }
}
