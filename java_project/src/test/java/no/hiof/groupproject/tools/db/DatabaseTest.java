package no.hiof.groupproject.tools.db;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void assertsMainDbExists() {
        File f = new File("sqlite/db/test.db");
        assertTrue(f.exists());
    }
    @Test
    void assertsTestableDbExists() {
        File f = new File("sqlite/db/testable.db");
        assertTrue(f.exists());
    }
    @Test
    void assertsConnectionEstablishedWithDatabase() {

    }
}