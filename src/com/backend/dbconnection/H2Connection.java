package com.backend.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class H2Connection {
    private static final String URL = "jdbc:h2:~/odontologos";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void ejecutarScriptInicial() {
        try (Connection connection = getConnection();
             BufferedReader br = new BufferedReader(new InputStreamReader(
                     H2Connection.class.getClassLoader().getResourceAsStream("create.sql")));
             Statement statement = connection.createStatement()) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            String script = sb.toString();
            statement.execute(script);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
