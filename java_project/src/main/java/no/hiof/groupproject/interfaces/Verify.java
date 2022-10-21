package no.hiof.groupproject.interfaces;
public interface Verify {

    Boolean verifyLicenseNumber(String number);
    Boolean verifyDateOfIssue(String dateOfIssue);
    String verifyCountryOfIssue(String country);

}
