package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.payment_methods.*;

/*
This class is used to verify a customer's payment. There are several methods of payment (Vipps, credit & debit cards,
Paypal, and Googlepay). The payment will return a value based on if it is accepted or declined, and an
explanation of a declined payment will be provided.
 */

public class VerifyPayment {


    private Payment payment_method;

    public VerifyPayment (Payment payment_method) {
        this.payment_method = payment_method;
    }

    public boolean isVerified() {
        if (payment_method.getClass() == Paypal.class) {
            Paypal payment = (Paypal)payment_method;
            return true;
        }
        else return false;
    }
}
