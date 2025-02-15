package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.ju23.typespeeder.Entitys.NewsLetterRepo;
import se.ju23.typespeeder.Entitys.PatchRepo;
import se.ju23.typespeeder.Entitys.PlayerRepo;
import se.ju23.typespeeder.Game.ChallengeService;
import se.ju23.typespeeder.DatabaseAndUtility.EntityManager;
import se.ju23.typespeeder.Game.DisplayService;
import se.ju23.typespeeder.Menu.*;


import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    @Mock
    private EntityManager entityManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
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
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        loginService = new LoginService(playerRepo);
        challenge = new ChallengeService(entityManager);
        display = new DisplayService(entityManager,patchRepo,newsLetterRepo);
    }


    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testClassExists() {
        try {
            Class<?> clazz = Class.forName("se.ju23.typespeeder.Menu.Menu");
            assertNotNull(clazz, "The class 'Menu' should exist.");
        } catch (ClassNotFoundException e) {
            fail("The class 'Menu' does not exist.", e);
        }
    }

    @Test
    public void testMethodExists() {
        try {
            Class<?> clazz = Class.forName("se.ju23.typespeeder.Menu.Menu");
            Method method = clazz.getMethod("displayMenu");
            assertNotNull(method, "The method 'displayMenu()' should exist in the class 'Menu'.");
        } catch (ClassNotFoundException e) {
            fail("The class 'Menu' does not exist.", e);
        } catch (NoSuchMethodException e) {
            fail("The method 'displayMenu()' does not exist in the class 'Menu'.", e);
        }
    }

    @Test
    public void testMenuImplementsInterface() {
        try {
            Class<?> menuClass = Class.forName("se.ju23.typespeeder.Menu.Menu");
            boolean implementsInterface = false;

            Class<?>[] interfaces = menuClass.getInterfaces();
            for (Class<?> iface : interfaces) {
                if (iface.equals(MenuService.class)) {
                    implementsInterface = true;
                    break;
                }
            }

            assertTrue(implementsInterface, "The class 'Menu' should implement the interface 'MenuService'.");
        } catch (ClassNotFoundException e) {
            fail("The class 'Menu' could not be found", e);
        }
    }
    @Test
    public void testDisplayMenuCallsgetEnglishMenuOptionsAndReturnsAtLeastFive() {
        Menu menu = new Menu(loginService,challenge,display,playerRepo);
        menu.displayMenu();
        assertTrue(menu.getEnglishMenuOptions().size() >= 5, "'getMenuOptions()' should return at least 5 alternatives.");
    }

    @Test
    public void testDisplayMenuCallsGetSwedishMenuOptionsAndReturnsAtLeastFive() {
        Menu menu = new Menu(loginService,challenge,display,playerRepo);
        menu.displayMenu();
        assertTrue(menu.getSwedishMenuOptions().size() >= 5, "'getMenuOptions()' should return at least 5 alternatives.");
    }

    @Test
    public void swedishMenuShouldHaveAtLeastFiveOptions() {
        Menu menu = new Menu(loginService,challenge,display,playerRepo);
        List<String> options = menu.getSwedishMenuOptions();
        assertTrue(options.size() >= 5, "The menu should contain at least 5 alternatives.");
    }
    @Test
    public void englishMenuShouldHaveAtLeastFiveOptions() {
        Menu menu = new Menu(loginService,challenge,display,playerRepo);
        List<String> options = menu.getEnglishMenuOptions();
        assertTrue(options.size() >= 5, "The menu should contain at least 5 alternatives.");
    }

    @Test
    public void menuShouldPrintAtLeastFiveOptions() {
        new Menu(loginService,challenge,display,playerRepo).displayMenu();
        long count = outContent.toString().lines().count();
        assertTrue(count >= 5, "The menu should print out at least 5 alternatives.");
    }

    @Test
    public void testUserCanChooseSwedishLanguage() {
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu menu = new Menu(loginService,challenge,display,playerRepo);
        menu.displayMenu();

        String selectedLanguage = challenge.selectLanguage();

        assertEquals("Svenska" , selectedLanguage);
    }
}