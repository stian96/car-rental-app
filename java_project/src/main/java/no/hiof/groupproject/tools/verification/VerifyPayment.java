package no.hiof.groupproject.tools.verification;

import no.hiof.groupproject.models.payment_methods.*;

import java.time.LocalDate;
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
    private HashMap<String, CreditDebitPair> cardVerification;

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

        cardVerification = new HashMap<>();
        cardVerification.put("1234234534564567", new CreditDebitPair("123", LocalDate.of(2024, 12, 1)));
        cardVerification.put("1111222233334444", new CreditDebitPair("444", LocalDate.of(2022, 9, 1)));

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
        //googlepay is basically the same as paypal in all but name. it can possibly be
        //expanded on later in the future, but remains flexible because both paypal and
        //googlepay have the PaymentViaAccount abstract superclass
        else if (payment_method.getClass() == GooglePay.class) {
            //downcast to GooglePay to access the getters
            GooglePay payment = (GooglePay)payment_method;
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
        else if (payment_method.getClass() == CreditDebit.class) {
            //downcast to CreditDebit to access the getters
            CreditDebit payment = (CreditDebit)payment_method;
            if (cardVerification.containsKey(payment.getCard_number())) {
                //checks that card number ccv combination is correct
                if (Objects.equals(cardVerification.get(payment.getCard_number()).getCcv(), payment.getCcv())) {
                    //IMPORTANT - checks to see if the card has expired as of current date
                    return payment.getValid_until().isAfter(LocalDate.now());
                }
            }
            return false;
        }
        else {
            return false;
        }
    }
}
