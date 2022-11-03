package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.payment_methods.*;
import no.hiof.groupproject.tools.License;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
Returns a specific Payment in the database based on the id of the Payment

 */
public class RetrievePaymentDB {

    public static Payment retrieve(int id) {

        String sql = "SELECT * FROM payments WHERE payment_id = " + id;

        Payment payment = null;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            String paymentType = queryResult.getString("paymentType");
            if (paymentType == "creditdebit") {
                String cardNumber = queryResult.getString("cardNumber");
                String ccv = queryResult.getString("ccv");
                LocalDate date = LocalDate.parse(queryResult.getString("validUntil"));
                payment = new CreditDebit(cardNumber, ccv, date.getMonthValue(), date.getYear());
            } else if (paymentType == "paypal") {
                String email = queryResult.getString("email");
                String pwd = queryResult.getString("pwd");
                payment = new Paypal(email, pwd);
            } else if (paymentType == "googlepay") {
                String email = queryResult.getString("email");
                String pwd = queryResult.getString("pwd");
                payment = new GooglePay(email, pwd);
            } else if (paymentType == "vipps") {
                String tlfnr = queryResult.getString("tlfnr");
                String pincode = queryResult.getString("pincode");
                payment = new Vipps(tlfnr, pincode);
            }
            return payment;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return payment;
    }


}