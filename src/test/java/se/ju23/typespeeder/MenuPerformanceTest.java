package se.ju23.typespeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.ju23.typespeeder.Challenge.Challenge;
import se.ju23.typespeeder.Database.DatabaseManager;
import se.ju23.typespeeder.Menu.LoginService;
import se.ju23.typespeeder.Menu.Menu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuPerformanceTest {
    @Mock
    private DatabaseManager databaseManager;
    private static final int MAX_EXECUTION_TIME_MENU = 1;
    private static final int MAX_EXECUTION_TIME_LANGUAGE_SELECTION = 100;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    private LoginService loginService;
    private  Challenge challenge;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        challenge = new Challenge(databaseManager);
        loginService = new LoginService(databaseManager);
    }

    @Test
    public void testGetMenuOptionsExecutionTime() {
        long startTime = System.nanoTime();
        Menu menu = new Menu(loginService,challenge);
        menu.getMenuOptions();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        assertTrue(duration <= MAX_EXECUTION_TIME_MENU, "Menu display took too long. Execution time: " + duration + " ms.");
    }

     @Test
    public void testUserCanChooseSwedishLanguageAndPerformance() {
        String input = "2\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        long startTime = System.nanoTime();
        Menu menu = new Menu(loginService, challenge);
        menu.displayMenu();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;


        String selectedLanguage = challenge.selectLanguage();

        assertEquals("Svenska", selectedLanguage);

        assertTrue(duration <= MAX_EXECUTION_TIME_LANGUAGE_SELECTION, "Menu display and language selection took too long. Execution time: " + duration + " ms.");
    }
}