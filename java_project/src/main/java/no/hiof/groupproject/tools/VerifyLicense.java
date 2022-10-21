package no.hiof.groupproject.tools;

import no.hiof.groupproject.interfaces.Verify;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class VerifyLicense implements Verify {
    private String licenseNumber;
    private String dateOfIssue;
    private String countryOfIssue;

    public VerifyLicense(String licenseNumber, String dateOfIssue, String countryOfIssue) {
        this.licenseNumber = licenseNumber;
        this.dateOfIssue = dateOfIssue;
        this.countryOfIssue = countryOfIssue;
    }

    @Override
    public Boolean verifyLicenseNumber(String licenseNumber) {
        return Pattern.matches("\\d\\d \\d\\d 123456 1", licenseNumber);
    }

    @Override
    public Boolean verifyDateOfIssue(String dateOfIssue) {
        LocalDate now = LocalDate.now();
        LocalDate usersDate = LocalDate.parse(dateOfIssue);

        return usersDate.isBefore(now);
    }

    @Override
    public String verifyCountryOfIssue(String country) {
        if (country.equalsIgnoreCase("Norway"))
            return "Valid";
        else
            return "License only valid for 3 months.";
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
