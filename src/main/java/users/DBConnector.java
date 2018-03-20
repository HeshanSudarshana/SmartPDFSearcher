package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnector {

    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/embedDB.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql1 = "CREATE TABLE USER " +
                    "(USERNAME      VARCHAR(50)     PRIMARY KEY     NOT NULL," +
                    " PASSWORD      VARCHAR(50)                     NOT NULL, " +
                    " WORKSPACE     VARCHAR                         NOT NULL)";
            stmt.executeUpdate(sql1);
            stmt.close();

            System.out.println("table1 success");

            stmt = c.createStatement();
            String sql2 = "CREATE TABLE HISTORY " +
                    "(USERNAME      VARCHAR(50)     NOT NULL        REFERENCES  USER ON UPDATE CASCADE ON DELETE CASCADE," +
                    " KEYWORD       VARCHAR(100)    NOT NULL, " +
                    " SEARCH_TYPE   VARCHAR(10)     NOT NULL, " +
                    " TIME          VARCHAR(30)     NOT NULL, " +
                    " PRIMARY KEY (USERNAME, KEYWORD, SEARCH_TYPE, TIME),"+
                    " CHECK (SEARCH_TYPE IN ('Name','Content')))";
            stmt.executeUpdate(sql2);
            stmt.close();

            System.out.println("table2 success");

            stmt = c.createStatement();
            String sql3 = "CREATE TABLE FAVOURITES " +
                    "(USERNAME      VARCHAR(50)     NOT NULL        REFERENCES  USER ON UPDATE CASCADE ON DELETE CASCADE," +
                    " PATH          VARCHAR         NOT NULL, " +
                    " PRIMARY KEY (USERNAME, PATH))";
            stmt.executeUpdate(sql3);
            stmt.close();

            System.out.println("table3 success");

            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

}
