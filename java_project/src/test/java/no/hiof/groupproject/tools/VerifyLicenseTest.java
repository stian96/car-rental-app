package no.hiof.groupproject.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyLicenseTest {

    VerifyLicense test = new VerifyLicense("98 45 123456 1", "2008-05-12",
            "Norway");

    @Test
    void CheckIfFunctionReturnTrueOnCorrectPattern() {
        assertEquals(true, test.verifyLicenseNumber(test.getLicenseNumber()));
    }

    @Test
    void CheckIfFunctionReturnFalseOnWrongPattern() {
        assertEquals(false, test.verifyLicenseNumber("9543 1234561"));
    }

    @Test
    void CheckIfDateOfIssueIsBeforeNow() {
        assertEquals(true, test.verifyDateOfIssue(test.getDateOfIssue()));
    }

    @Test
    void CheckIfCountryOfIssueIsNorway() {
        assertEquals("Valid", test.verifyCountryOfIssue(test.getCountryOfIssue()));
    }

    @Test
    void CheckIfCountryOfIssueIsNotNorway() {
        String rules = "License only valid for 3 months.";
        assertEquals(rules, test.verifyCountryOfIssue("Poland"));
    }

}


