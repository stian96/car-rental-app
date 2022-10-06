package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.payment_methods.*;

import java.util.HashMap;
import java.util.Objects;

/*
This class is used to verify a customer's payment. There are several methods of payment (Vipps, credit & debit cards,
Paypal, and Googlepay). The payment will return a value based on if it is accepted or declined, and an
explanation of a declined payment will be provided.
 */

public class VerifyPayment {


    private Payment payment_method;
    //dummy dictionary of valid username and password combinations
    private HashMap<String, String> paypalVerification;
    private HashMap<String, String> vippsVerification;

    public VerifyPayment (Payment payment_method) {
        this.payment_method = payment_method;

        //inserting valid username password combinations into hashmap
        paypalVerification = new HashMap<>();
        paypalVerification.put("jim@hotmail.com", "hunter2");
        paypalVerification.put("joris@gmail.fi", "PeacockLover");
        paypalVerification.put("alphabetlover@hotmail.co.uk", "pwd123");

        //inserting valid tlfnr and pincode combinations into a hashmap
        vippsVerification = new HashMap<>();
        vippsVerification.put("12345678", "1234");
        vippsVerification.put("22233312", "1569");
        vippsVerification.put("12423243", "9876");
    }

    //method to test if the details given are valid
    public boolean isVerified() {
        //paypal only
        if (payment_method.getClass() == Paypal.class) {
            //downcast to Paypal to access the getters
            Paypal payment = (Paypal)payment_method;
            //checks to see if email is in hashmap
            if (paypalVerification.containsKey(payment.getEmail())) {
                //checks that email password combination is correct
                return Objects.equals(paypalVerification.get(payment.getEmail()), payment.getPwd());
            }
            return false;
        }
        else if (payment_method.getClass() == Vipps.class) {
            //downcast to Vipps to access the getters
            Vipps payment = (Vipps)payment_method;
            if (vippsVerification.containsKey(payment.getTlfnr())) {
                //checks that tlfnr pincode combination is correct
                return Objects.equals(vippsVerification.get(payment.getTlfnr()), payment.getPincode());
            }
            return false;
        }
        else {
            return false;
        }
    }
}
