package no.hiof.groupproject.tools.db;

import java.sql.Connection;

/*
Uses the ConnectSpecifiedDB to create a simple class to always ensure data consistency by always connecting to
the same DB.
 */
public class ConnectDB {

    static String db = "jdbc:sqlite:sqlite/db/test.db";

    public static Connection connect() {

        return ConnectSpecifiedDB.connect(db);
    }

    public static Connection connectReadOnly() {

        return ConnectSpecifiedDB.connectReadOnly(db);
    }

    public static String getDb() {
        return db;
    }

    public static void setDb(String db) {
        ConnectDB.db = db;
    }
}