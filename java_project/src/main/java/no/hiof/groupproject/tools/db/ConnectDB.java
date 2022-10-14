package no.hiof.groupproject.tools.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
A loose coupled adaptor which establishes a connection to a database.
Simply import this class and call the static method via:
    ConnectDB.connect();
The code was taken from https://www.sqlitetutorial.net/sqlite-java/create-database/ at 13:00
on the 14/10/2022.
@author sqlitetutorial.net
 */
public class ConnectDB {

    public static void connect() {

        Connection conn = null;

        try {
            //path location of the database
            String url = "jdbc:sqlite:sqlite/db/test.db";
            //establish a database connection
            conn = DriverManager.getConnection(url);

            //feedback confirming database is connected
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}