package no.hiof.groupproject.tools;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LicenseTest {

    License test = new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
            "Norway");

    @Test
    void CheckIfFunctionReturnTrueOnCorrectPattern() {
        assertEquals(true, test.verifyLicenseNumber());
    }

    @Test
    void CheckIfFunctionReturnFalseOnWrongPattern() {

        License test = new License("fdsgfdsg", LocalDate.parse("2008-05-12"),
                "Norway");

        assertEquals(false, test.verifyLicenseNumber());
    }

    @Test
    void CheckIfDateOfIssueIsBeforeNow() {
        assertEquals(true, test.verifyDateOfIssue());
    }

    @Test
    void CheckIfCountryOfIssueIsNorway() {
        assertEquals("Valid", test.verifyCountryOfIssue());
    }

    @Test
    void CheckIfCountryOfIssueIsNotNorway() {
        License test = new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
                "Poland");
        String rules = "License only valid for 3 months.";
        assertEquals(rules, test.verifyCountryOfIssue());
    }

}


