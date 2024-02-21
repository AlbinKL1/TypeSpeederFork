package se.ju23.typespeeder.Menu;

import java.util.List;

public interface MenuService {

    void loginMenu();
    void displayMenu();
    List<String> getEnglishMenuOptions();
    List<String> getSwedishMenuOptions();
}
