package user_access;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBConnector {

    // add user to database

    public void addUser(User user) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

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

    //add history to database (keeping records)

    public void addToHistory(String username, String path, String searchType, String searchPath) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("INSERT INTO history (username, keyword, search_type, time, search_path) VALUES (?, ?, ?, ?, ?);");
            stmt.setString(1, username);
            stmt.setString(2, path);
            stmt.setString(3, searchType);
            stmt.setString(4, searchPath);

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
        System.out.println("Added to History successfully");
    }

    //add favourites to the database

    public void addToFavourites(String username, String path) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("INSERT INTO favourites (username, path) VALUES (?, ?);");
            stmt.setString(1, username);
            stmt.setString(2, path);
            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("Added to Favourites successfully");
    }

    //update user

    public void updateUser(int userID, String username, String password, String workspace) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("UPDATE user SET username = ?, password = ?, workspace = ? " +
                                            "WHERE userid = ?;");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, workspace);
            stmt.setInt(4, userID);
            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("User updated successfully");
    }

    //delete user

    public void deleteUser(String username) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("DELETE FROM user WHERE username = ?;");
            stmt.setString(1, username);

            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("User deleted successfully");
    }

    //return user details

    public User getUserDetails(String name) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("SELECT * FROM user WHERE username = ?;");
            stmt.setString(1, name);

            ResultSet resultSet = stmt.executeQuery();

            User user = new User("","","");

            while (resultSet.next()) {
                int id = resultSet.getInt("userid");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String workspace = resultSet.getString("workspace");

                user = new User(username, password, workspace);
                user.setUserID(id);
            }

            stmt.close();

            c.close();

            System.out.println("User returned successfully");

            return user;

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;

        }

    }

    //return history details

    public ArrayList<HistoryObject> getHistory(int userID) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("SELECT * FROM history WHERE userid = ?;");
            stmt.setInt(1, userID);

            ResultSet resultSet = stmt.executeQuery();

            ArrayList<HistoryObject> list = new ArrayList<>();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String keyword = resultSet.getString("keyword");
                String search_type = resultSet.getString("search_type");
                String time = resultSet.getString("time");
                String search_path = resultSet.getString("search_path");

                HistoryObject temp = new HistoryObject(username, keyword, search_type, time, search_path);
                list.add(temp);
            }

            stmt.close();

            c.close();

            System.out.println("History returned successfully");

            return list;

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;

        }

    }

    //return favourite details

    public ArrayList<FavouriteObject> getFavourites(int userID) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("SELECT * FROM favourites WHERE userid = ?;");
            stmt.setInt(1, userID);

            ResultSet resultSet = stmt.executeQuery();

            ArrayList<FavouriteObject> list = new ArrayList<>();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String path = resultSet.getString("path");

                FavouriteObject temp = new FavouriteObject(username, path);
                list.add(temp);
            }

            stmt.close();

            c.close();

            System.out.println("Favourites returned successfully");

            return list;

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;

        }

    }

    //delete history entry

    public void deleteHistoryObject(String username, String keyword, String search_type, String time, String search_path) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("DELETE FROM history WHERE username = ? AND keyword = ? AND search_type = ? AND time = ? AND search_path = ?;");
            stmt.setString(1, username);
            stmt.setString(2, keyword);
            stmt.setString(3, search_type);
            stmt.setString(4, time);
            stmt.setString(5, search_path);

            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("History entry deleted successfully");
    }

    //delete all history

    public void deleteAllHistory(String username) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("DELETE FROM history WHERE username = ?;");
            stmt.setString(1, username);

            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("Deleted all history successfully");
    }

    //delete a favourite

    public void deleteFavoriteObject(String username, String path) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("DELETE FROM favourites WHERE username = ? AND path = ?;");
            stmt.setString(1, username);
            stmt.setString(2, path);

            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("Favourite object was deleted successfully");
    }

    //delete all favourites

    public void deleteAllFavourites(String username) {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("DELETE FROM favourites WHERE username = ?;");
            stmt.setString(1, username);

            stmt.executeUpdate();
            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        System.out.println("Deleted all favourites successfully");
    }

    //get all user names

    public ArrayList<String> getUsernameList() {

        Connection c = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            Statement statement = c.createStatement();
            statement.executeUpdate("PRAGMA foreign_keys = ON ;");

            stmt = c.prepareStatement("SELECT * FROM user;");

            ResultSet resultSet = stmt.executeQuery();

            ArrayList <String> list = new ArrayList<>();

            while (resultSet.next()) {
                String username = resultSet.getString("username");

                list.add(username);
            }

            stmt.close();

            c.close();

            System.out.println("User returned successfully");

            return list;

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;

        }

    }

    //to output user table

    public static void printTable() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user;");

            while (rs.next()) {
                String name = rs.getString("username");
                String pwd = rs.getString("password");
                String work = rs.getString("workspace");

                System.out.println("NAME = " + name);
                System.out.println("PWD = " + pwd);
                System.out.println("WORKSPACE = " + work);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
    }

    //to output history table

    public static void printHistory() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM history;");

            while (rs.next()) {
                String name = rs.getString("username");
                String keyword = rs.getString("keyword");
                String search_type = rs.getString("search_type");
                String time = rs.getString("time");

                System.out.println("NAME = " + name);
                System.out.println("key = " + keyword);
                System.out.println("search = " + search_type);
                System.out.println("time = " + time);
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
