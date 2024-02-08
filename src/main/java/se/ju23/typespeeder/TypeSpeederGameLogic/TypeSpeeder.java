package se.ju23.typespeeder.TypeSpeederGameLogic;

import se.ju23.typespeeder.Database.Database;
import se.ju23.typespeeder.IO.IO;

public class TypeSpeeder implements Game {
    private Database database;
    private IO io;
    private static int id;

    public static void setPlayerId(int playerId) {
        id = playerId;
    }
}
