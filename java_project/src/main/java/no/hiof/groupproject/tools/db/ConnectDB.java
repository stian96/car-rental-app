package no.hiof.groupproject.tools.db;

import java.sql.Connection;

/*
Uses the ConnectSpecifiedDB to create a simple class to always ensure data consistency by always connecting to
the same DB.
 */
public class ConnectDB {

    public static Connection connect() {

        return ConnectSpecifiedDB.connect("jdbc:sqlite:sqlite/db/test.db");
    }
}