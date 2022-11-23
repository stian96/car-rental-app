package no.hiof.groupproject.models;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.interfaces.VerifyLicense;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.InsertLicenseDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class License implements Serialise, ExistsInDb, VerifyLicense {

    private String licenseNumber;
    private LocalDate dateOfIssue;
    private String countryOfIssue;

    public License(String licenseNumber, LocalDate dateOfIssue, String countryOfIssue) {
        this.licenseNumber = licenseNumber;
        this.dateOfIssue = dateOfIssue;
        this.countryOfIssue = countryOfIssue;

        //serialisation done as long as license doesn't exist in the DB, and licenseNumber
        //and dateOfIssue are valid
        if (this.verifyLicenseNumber() && this.verifyDateOfIssue()) {
            if (!existsInDb()) {
                serialise();
            }
        }

    }

    @Override
    public Boolean verifyLicenseNumber() {
        return Pattern.matches("\\d\\d \\d\\d 123456 1", this.licenseNumber);
    }

    @Override
    public Boolean verifyDateOfIssue() {
        LocalDate now = LocalDate.now();
        LocalDate usersDate = LocalDate.parse(this.dateOfIssue.toString());

        return usersDate.isBefore(now);
    }

    @Override
    public String verifyCountryOfIssue() {
        if (this.countryOfIssue.equalsIgnoreCase("Norway") ||
                this.countryOfIssue.equalsIgnoreCase("Norge"))
            return "Valid";
        else
            return "License only valid for 3 months.";
    }

    @Override
    public void serialise() {
        InsertLicenseDB.insert(this);
    }

    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM licenses WHERE licenseNumber = \'" + this.licenseNumber + "\'";

        boolean ans = false;
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

}