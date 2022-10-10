package no.hiof.groupproject.tools.Tool;

import java.util.HashMap;
import java.util.Locale;

public class VerifyInsurance {
    private int carId;
    private final static String HAS_INSURANCE = "yes";
    private final static String NO_INSURANCE = "no";
    private HashMap<Integer, String> insurance;

    public VerifyInsurance(int carID) {
        this.carId = carID;
        this.insurance = new HashMap<>();
    }

    public Boolean VerifyCarInsurance(String response) {
        String result = response.toLowerCase(Locale.ROOT);
        registerResult(result);
        return insurance.get(0).equals(result);
    }

    public void registerResult(String response) {
        if (response.equals(HAS_INSURANCE)) {
            insurance.put(carId, HAS_INSURANCE);
        }
        else {
            insurance.put(carId, NO_INSURANCE);
        }

    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public HashMap<Integer, String> getInsurance() {
        return insurance;
    }

    public void setInsurance(HashMap<Integer, String> insurance) {
        this.insurance = insurance;
    }
}
