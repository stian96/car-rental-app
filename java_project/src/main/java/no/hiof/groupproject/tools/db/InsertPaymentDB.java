package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.payment_methods.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/*
A class used to serialise a Booking into a database for permanent storage.
 */
public class InsertPaymentDB {

    public static void insert(Payment payment) {

        String sql = "INSERT INTO payments (paymentType, cardNumber, ccv, validUntil, email, pwd, tlfnr, pincode)" +
                "VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setString(1, payment.getPaymentType());
            if (Objects.equals(payment.getPaymentType(), "creditdebit")) {
                str.setString(2, ((CreditDebit) payment).getCard_number());
                str.setString(3, ((CreditDebit) payment).getCcv());
                str.setString(4, ((CreditDebit) payment).getValid_until().toString());
            } else if (Objects.equals(payment.getPaymentType(), "googlepay")) {
                str.setString(5, ((GooglePay) payment).getEmail());
                str.setString(6, ((GooglePay) payment).getPwd());
            } else if (Objects.equals(payment.getPaymentType(), "paypal")) {
                str.setString(5, ((Paypal) payment).getEmail());
                str.setString(6, ((Paypal) payment).getPwd());
            } else if (Objects.equals(payment.getPaymentType(), "vipps")) {
                str.setString(7, ((Vipps) payment).getTlfnr());
                str.setString(8, ((Vipps) payment).getPincode());
            }
            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

   /* public static void insertWithPriorConnection(Payment payment, Connection conn) throws SQLException {

        String sql = "INSERT INTO payments (paymentType, cardNumber, ccv, validUntil, email, pwd, tlfnr, pincode)" +
                "VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement str = conn.prepareStatement(sql);
        str.setString(1, payment.getPaymentType());
        if (Objects.equals(payment.getPaymentType(), "creditdebit")) {
            str.setString(2, ((CreditDebit) payment).getCard_number());
            str.setString(3, ((CreditDebit) payment).getCcv());
            str.setString(4, ((CreditDebit) payment).getValid_until().toString());
        } else if (Objects.equals(payment.getPaymentType(), "googlepay")) {
            str.setString(5, ((GooglePay) payment).getEmail());
            str.setString(6, ((GooglePay) payment).getPwd());
        } else if (Objects.equals(payment.getPaymentType(), "paypal")) {
            str.setString(5, ((Paypal) payment).getEmail());
            str.setString(6, ((Paypal) payment).getPwd());
        } else if (Objects.equals(payment.getPaymentType(), "vipps")) {
            str.setString(7, ((Vipps) payment).getTlfnr());
            str.setString(8, ((Vipps) payment).getPincode());
        }
        str.executeUpdate();

    }*/
}