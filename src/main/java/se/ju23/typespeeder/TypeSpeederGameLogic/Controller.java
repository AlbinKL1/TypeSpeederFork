package se.ju23.typespeeder.TypeSpeederGameLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Database.Database;
import se.ju23.typespeeder.IO.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class Controller {
    private Database database;
    private IO io;
    private Game game;

    @Autowired
    public Controller(Database database, IO io) {
        this.database = database;
        this.io = io;
    }

    public void runGame() throws SQLException, ClassNotFoundException, InterruptedException {
        boolean loggedIn = false;
        ResultSet rs = null;
        String name = "";

        while (!loggedIn) {
            io.addString("Enter 'login' to log in or 'register' to create an account (type 'exit' to exit):\n");
            String option = io.getString();

            switch (option.toLowerCase()) {
                case "exit":
                    io.exit();
                    break;
                case "login":
                    name = login();
                    if (name != null) {
                        loggedIn = true;
                    }
                    break;
                case "register":
                    //register();
                    break;
                default:
                    io.clear();
                    io.addString("Invalid option. Please try again.\n");
                    break;
            }
        }
        String language = selectLanguage();
        displayMenu(language);
    }

    private String login() throws SQLException {
        io.addString("Enter your user name:\n");
        String name = io.getString();

        if (name.equalsIgnoreCase("exit")) {
            io.exit();
        }

        io.addString("Enter your password:\n");
        String password = io.getString();

        ResultSet rs = database.getPlayerByLogin(name, password);
        String username = null;

        try {
            if (rs.next()) {
                int id = rs.getInt("id");
                TypeSpeeder.setPlayerId(id);
                username = name;
            } else {
                io.clear();
                io.addString("Incorrect username or password. Please try again or register with admin.\n");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return username;
    }
    private String selectLanguage() {
        io.addString("Select language: 'swedish' or 'english'\n");
        String language = io.getString();
        return language.toLowerCase();
    }

    private void displayMenu(String language) {
        switch (language) {
            case "swedish":
                io.addString("Swedish");
                break;
            case "english":
                io.addString("English");
                break;
            default:
                io.addString("Invalid language selection. Displaying English menu by default.\n");
                break;
        }
    }
}

