package se.ju23.typespeeder.Database;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Component;


@Component
public class SqlDatabase implements Database {
    private final Connection connection;

    public SqlDatabase() throws SQLException {
        String url = DatabaseLogin.URL;
        String username = DatabaseLogin.USERNAME;
        String password = DatabaseLogin.PASSWORD;
        connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public int save(int nGuess, int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO results (result, id) VALUES (" + nGuess + ", " + id + ")");
            return nGuess;
        }
    }

    @Override
    public ResultSet getPlayerByLogin(String LoginName, String password) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, LoginName FROM player WHERE LoginName = ? AND password = ?")) {
            statement.setString(1, LoginName);
            statement.setString(2, password);
            return statement.executeQuery();
        }
    }


    @Override
    public ResultSet getAllPlayers() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery("select * from player");
        }
    }

    @Override
    public ResultSet getResultById(int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery("select * from results where id = " + id);
        }
    }
}
