package no.hiof.groupproject.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExampleToolClassTest {

    ExampleToolClass testClass = new ExampleToolClass();

    @Test
    void ChecksThatFunctionPrintsCorrectMessage() {
        String comparison = "Hello :)";
        assertEquals(comparison, testClass.returnHello());
    }
}