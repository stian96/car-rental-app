package no.hiof.groupproject.tools.Interface;
public interface Verify {

    Boolean verifyLicenseNumber(String number);
    Boolean verifyDateOfIssue(String dateOfIssue);
    String verifyCountryOfIssue(String country);

}
