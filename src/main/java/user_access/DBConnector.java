package user_access;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBConnector {

    public static void main(String[] args) {

        printTable();

    }

    public void addUser(User user) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            stmt = c.prepareStatement("INSERT INTO user (username, password, workspace) VALUES (?, ?, ?);");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getWorkspacePath());
            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("User added successfully");
    }

    public void addToHistory(String username, String path, String searchType) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            stmt = c.prepareStatement("INSERT INTO history (username, keyword, search_type, time) VALUES (?, ?, ?, ?);");
            stmt.setString(1, username);
            stmt.setString(2, path);
            stmt.setString(3, searchType);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = now.format(dtf);

            stmt.setString(4, date);
            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("User added successfully");
    }


    public void addToFavourites(String username, String path) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            stmt = c.prepareStatement("INSERT INTO favourites (username, path) VALUES (?, ?);");
            stmt.setString(1, username);
            stmt.setString(2, path);
            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("User added successfully");
    }




    public static void printTable() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM favourites;");

            while (rs.next()) {
                String name = rs.getString("username");
                String path = rs.getString("path");

                System.out.println("NAME = " + name);
                System.out.println("PATH = " + path);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
    }

}
