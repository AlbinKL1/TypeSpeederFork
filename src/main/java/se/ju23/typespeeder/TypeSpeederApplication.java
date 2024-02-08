package se.ju23.typespeeder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.ju23.typespeeder.Database.Database;
import se.ju23.typespeeder.Database.SqlDatabase;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.IO.SystemIO;
import se.ju23.typespeeder.TypeSpeederGameLogic.Controller;

import java.sql.SQLException;

@SpringBootApplication
public class TypeSpeederApplication {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Database database = new SqlDatabase();
        IO io = new SystemIO();
        Controller controller = new Controller(database,io);

        controller.runGame();
    }
}