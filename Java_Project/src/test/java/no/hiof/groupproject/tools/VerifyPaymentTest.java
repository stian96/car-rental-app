package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.payment_methods.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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

        payment = new CreditDebit();
        assertTrue(payment instanceof Payment && payment.getClass() == CreditDebit.class);
    }

    @Test
    void assertsCreditDebitNumberIsOfValidFormat() {
        payment = new CreditDebit("5459 2354 2546 9648", "524", 12, 2011);

        //downcasting Payment to Credit_Debit
        CreditDebit payment_downcast = (CreditDebit)payment;
        //checking that all spaces are removed
        assertEquals(payment_downcast.getCard_number(), "5459235425469648");
    }

    @Test
    void assertsCreditDebitRejectsCardNumberLengthThatIsNotSixteen() {

        //checks that only 16 digit card numbers can be entered
        assertThrows(IllegalArgumentException.class, () -> payment = new CreditDebit("555 555", "524", 12, 2011));
    }

    @Test
    void assertsCreditDebitRejectsCCVLengthThatIsNotThree() {

        //checks that only 3 digit CCV numbers can be entered
        assertThrows(IllegalArgumentException.class, () -> payment = new CreditDebit("5459 2354 2546 9648", "54534524", 12, 2011));
    }

    @Test
    void assertsCreditDebitRejectsMonthLengthThatIsNotOneOrTwo() {

        //checks that only months 1-12 can be entered
        assertThrows(IllegalArgumentException.class, () -> payment = new CreditDebit("5459 2354 2546 9648", "524", 122, 2011));
    }

    @Test
    void assertsCreditDebitRejectsYearLengthThatIsNotFour() {

        //checks that only 4 digit years can be entered
        assertThrows(IllegalArgumentException.class, () -> payment = new CreditDebit("5459 2354 2546 9648", "524", 12, 20111));
    }

    @Test
    void assertsCreditDebitMonthRejectsZeroBeingPassed() {

        //checks that a single zero can't be entered
        assertThrows(IllegalArgumentException.class, () -> payment = new CreditDebit("5459 2354 2546 9648", "524", 0, 2011));
    }

    @Test
    void assertsVippsPincodeLengthIsFour() {

        //checks that a single zero can't be entered
        assertThrows(IllegalArgumentException.class, () -> payment = new Vipps("6546 5345", "9"));
    }

    @Test
    void assertsVippsTlfnrIsAtLeastEight() {

        //checks that a single zero can't be entered
        assertThrows(IllegalArgumentException.class, () -> payment = new Vipps("1 2 3 4 5 6 7", "1234"));
    }

    @Test
    void assertsPaymentViaAccountEmailContainsAtSymbol() {

        //checks that email does not have an '@' symbol in it
        assertThrows(IllegalArgumentException.class, () -> payment = new Paypal("emailathotmail.com", "hunter2"));
    }

    @Test
    void assertsValidEmailIfAtSymbolIsPresent() {

        //checks that email has an '@' symbol in it
        assertDoesNotThrow(() ->payment = new Paypal("email@hotmail.com", "hunter2"));
    }

    @Test
    void assertsInvalidPasswordIfLengthIsZero() {

        //checks that password is not empty
        assertThrows(IllegalArgumentException.class, () -> payment = new Paypal("email@hotmail.com", ""));
    }



}