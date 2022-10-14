package no.hiof.groupproject.tools.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
A loose coupled adaptor which creates a database in a specified path.
Simply import this class and call the static method via:
    CreateDB.createNewDatabase("exampleName.db");
The code was taken from https://www.sqlitetutorial.net/sqlite-java/create-database/ at 13:00
on the 14/10/2022.
@author sqlitetutorial.net
 */
public class CreateDB {

    public static void createNewDatabase(String fileName) {

        //specifying the path of the database
        String url = "jdbc:sqlite:sqlite/db/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                //feedback to confirm that everything is working
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        createNewDatabase("test.db");
    }
}