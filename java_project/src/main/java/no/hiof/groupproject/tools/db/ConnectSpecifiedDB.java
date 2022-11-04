package no.hiof.groupproject.tools.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Establishes a connection to a database.
Example of importing this class and calling the static method via:
    ConnectDB.connect(<String of DB URL>);
    such as : ConnectSpecifiedDB.connect("jdbc:sqlite:sqlite/db/test.db");
The code was taken from https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/ at 13:00
on the 14/10/2022.
@author sqlitetutorial.net
 */
public class ConnectSpecifiedDB {

    public static Connection connect(String specificURL) {

        Connection conn = null;

        try {
            //path location of the database
            //establish a database connection
            conn = DriverManager.getConnection(specificURL);

            //feedback confirming database is connected
            //System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //returns a variable of type Connection
        return conn;
    }
}