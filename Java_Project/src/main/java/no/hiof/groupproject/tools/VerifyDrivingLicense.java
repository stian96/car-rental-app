package no.hiof.groupproject.tools;
import java.util.HashMap;
/*
  A class that verifies if a person who is renting
  a car has a driving license or not.

  This is supposed to be checked by a third party
  service in a real situation.
*/
public class VerifyDrivingLicense {
    // Hashmap where we can store license classes for a specific person.
    private static HashMap<String, Boolean> licenseClasses = new HashMap<>();

    public Boolean verifyDrivingLicense(String licenseClass) {
        if (licenseClasses.containsKey(licenseClass)) {
            licenseClasses.put(licenseClass, true);
            return licenseClasses.get(licenseClass);
        }
        else {
           return licenseClasses.containsKey(licenseClass);
        }
    }

    /* Method that updates the parameter 'value' with either true or false,
       based on what it receives from the verifyDrivingLicense method. */
    public void fillLicenseDictionary() {
        boolean notVerified = false;
        licenseClasses.put("A", notVerified);
        licenseClasses.put("B", notVerified);
        licenseClasses.put("C", notVerified);
    }

    public HashMap<String, Boolean> getLicenseClasses() {
        return licenseClasses;
    }

    public void setLicenseClasses(HashMap<String, Boolean> licenseClasses) {
        VerifyDrivingLicense.licenseClasses = licenseClasses;
    }
}
