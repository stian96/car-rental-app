package no.hiof.groupproject.models;

import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import no.hiof.groupproject.tools.db.UpdateDB;
import no.hiof.groupproject.tools.geocode.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SerialiseTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }

    @Test
    void assertsMockTest() {

    }
}