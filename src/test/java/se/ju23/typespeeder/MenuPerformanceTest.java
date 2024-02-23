package se.ju23.typespeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.ju23.typespeeder.Entitys.NewsLetterRepo;
import se.ju23.typespeeder.Entitys.PatchRepo;
import se.ju23.typespeeder.Entitys.PlayerRepo;
import se.ju23.typespeeder.Game.ChallengeService;
import se.ju23.typespeeder.DatabaseAndUtility.EntityManager;
import se.ju23.typespeeder.Game.DisplayService;
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
    private EntityManager entityManager;
    private static final int MAX_EXECUTION_TIME_MENU = 1;
    private static final int MAX_EXECUTION_TIME_LANGUAGE_SELECTION = 100;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    private LoginService loginService;
    private ChallengeService challenge;
    private DisplayService display;
    @Mock
    private PlayerRepo playerRepo;
    @Mock
    private NewsLetterRepo newsLetterRepo;
    @Mock
    private PatchRepo patchRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        loginService = new LoginService(playerRepo);
        challenge = new ChallengeService(entityManager);
        display = new DisplayService(entityManager, patchRepo, newsLetterRepo);
    }
    @Test
    public void testGetSwedishMenuOptionsExecutionTime() {
        long startTime = System.nanoTime();
        Menu menu = new Menu(loginService,challenge,display,playerRepo);
        menu.getSwedishMenuOptions();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        assertTrue(duration <= MAX_EXECUTION_TIME_MENU, "Menu display took too long. Execution time: " + duration + " ms.");
    }
    @Test
    public void testGetEnglishMenuOptionsExecutionTime() {
        long startTime = System.nanoTime();
        Menu menu = new Menu(loginService,challenge,display,playerRepo);
        menu.getEnglishMenuOptions();
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

         Menu menu = new Menu(loginService,challenge,display,playerRepo);
         menu.displayMenu();

         long endTime = System.nanoTime();
         long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

         String consoleOutput = outContent.toString();

         assertTrue(consoleOutput.contains("Player menu"), "Menu should display the player menu.");
         assertTrue(consoleOutput.contains("Svenska valt."), "Menu should confirm Swedish language selection.");



         assertTrue(duration <= MAX_EXECUTION_TIME_LANGUAGE_SELECTION, "Menu display and language selection took too long. Execution time: " + duration + " ms.");
    }
}