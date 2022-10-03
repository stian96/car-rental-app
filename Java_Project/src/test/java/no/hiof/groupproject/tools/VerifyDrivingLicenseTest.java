package no.hiof.groupproject.tools;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class VerifyDrivingLicenseTest {

    VerifyDrivingLicense testClass = new VerifyDrivingLicense();

    @Test
    void CheckForDrivingLicense() {
        boolean compare = true;
        testClass.fillLicenseDictionary();
        assertEquals(compare, testClass.verifyDrivingLicense("B"));
    }

    @Test
    void CheckForNoLicense() {
        boolean compare = false;
        testClass.fillLicenseDictionary();
        assertEquals(compare, testClass.verifyDrivingLicense("BE"));
    }
}
