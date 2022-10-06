package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.payment_methods.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyPaymentTest {

    Payment payment;

    @Test
    void assertsThatSubclassObjectIsOfSuperclassTypePayment() {
        //checks that the payment class is polymorphic
        payment = new Paypal();
        assertTrue(payment instanceof Payment && payment.getClass() == Paypal.class);

        payment = new Vipps();
        assertTrue(payment instanceof Payment && payment.getClass() == Vipps.class);

        payment = new GooglePay();
        assertTrue(payment instanceof Payment && payment.getClass() == GooglePay.class);

        payment = new Credit_Debit();
        assertTrue(payment instanceof Payment && payment.getClass() == Credit_Debit.class);
    }

}