package no.hiof.groupproject.tools;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyInsuranceTest {

    VerifyInsurance insuranceTest = new VerifyInsurance();

    @Test
    void TestIfFunctionReturnInsuredIfInsured() {
        String hasInsurance = insuranceTest.VerifyCarInsurance("yes");
        assertEquals("insured", hasInsurance);
    }

    @Test
    void TestIfFunctionReturnNotInsuredIfNotInsured() {
        String noInsurance = insuranceTest.VerifyCarInsurance("no");
        assertEquals("not insured", noInsurance);
    }

    @Test
    void TestIfResultsGetRegisteredInHashMap() {
        insuranceTest.VerifyCarInsurance("yes");
        assertTrue(insuranceTest.getInsurance().containsKey(1));
    }



}