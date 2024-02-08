package se.ju23.typespeeder.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {
    Connection getConnection() throws SQLException, ClassNotFoundException;
    int save(int nGuess, int id) throws SQLException;
    ResultSet getPlayerByLogin(String name, String password)throws SQLException;
    ResultSet getAllPlayers()throws SQLException;
    ResultSet getResultById(int playerId) throws SQLException;
}
