package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.ju23.typespeeder.Menu.Menu;

@SpringBootApplication
public class TypeSpeederApplication implements CommandLineRunner {

    private final Menu menu;

    @Autowired
    public TypeSpeederApplication(Menu menu) {
        this.menu = menu;
    }

    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);
    }
    @Override
    public void run(String[] args) throws Exception {
        menu.displayMenu();
    }
}