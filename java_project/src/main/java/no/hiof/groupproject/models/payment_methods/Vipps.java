package no.hiof.groupproject.models.payment_methods;

import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.tools.db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vipps extends Payment {

    //string for these details to account for a 0 appearing
    private String tlfnr;
    private String pincode;

    public Vipps(String tlfnr, String pincode) {

        //removes all spaces and characters such as \n
        tlfnr = tlfnr.replaceAll("\\s+", "");
        pincode = pincode.replaceAll("\\s+", "");

        if (!checkLength(pincode, 4)) {
            throw new IllegalArgumentException();
        }
        else if (tlfnr.length() < 8) {
            throw new IllegalArgumentException();
        }
        else {
            this.tlfnr = tlfnr;
            this.pincode = pincode;
        }

        this.setPaymentType("vipps");

        if (!existsInDb()) {
            serialise();
        }
        //the id is automatically incremented when inserted into the database
        //the autoincrement id is fetched and assigned to this instance
        this.setId(getAutoIncrementId());

    }

    public Vipps() {

    }

    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM payments WHERE tlfnr = \'" + this.tlfnr + "\'";

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

    @Override
    public int getAutoIncrementId() {
        String sql = "SELECT * FROM payments WHERE tlfnr = \'" + this.tlfnr + "\'";

        int i = 0;
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            i = queryResult.getInt("payment_id");
            return i;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    public String getTlfnr() {
        return tlfnr;
    }

    public void setTlfnr(String tlfnr) {
        this.tlfnr = tlfnr;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
