package no.hiof.groupproject.tools.verification;

import java.util.HashMap;
import java.util.Locale;

/* The class is supposed to be handled by a third party */

public class VerifyInsurance {
    private int carId = 0;
    private final static String HAS_INSURANCE = "insured";
    private final static String NO_INSURANCE = "not insured";
    private HashMap<Integer, String> insurance = new HashMap<>();

    public String VerifyCarInsurance(String response) {
        String result = response.toLowerCase(Locale.ROOT);
        carId++;
        registerResult(result);
        return insurance.get(carId);
    }

    public void registerResult(String response) {
        if (response.equals("yes")) {
            insurance.put(carId, HAS_INSURANCE);
        }
        else {
            insurance.put(carId, NO_INSURANCE);
        }
    }

    public HashMap<Integer, String> getInsurance() {
        return insurance;
    }

    public void setInsurance(HashMap<Integer, String> insurance) {
        this.insurance = insurance;
    }
}