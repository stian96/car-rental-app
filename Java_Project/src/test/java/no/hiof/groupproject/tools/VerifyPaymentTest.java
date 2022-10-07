package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.payment_methods.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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

    @Test
    void assertPaypalEmailPasswordVerified() {

        //checks that the paypal email password combination is verified
        VerifyPayment payment = new VerifyPayment(new Paypal("jim@hotmail.com", "hunter2"));
        assertTrue(payment.isVerified());
    }

    @Test
    void assertPaypalPasswordIncorrect() {

        //checks that a valid email with invalid password returns false
        VerifyPayment payment = new VerifyPayment(new Paypal("jim@hotmail.com", "hunter3"));
        assertFalse(payment.isVerified());
    }

    @Test
    void assertPaypalEmailNotInHashmap() {

        //checks that non-existant emails return false
        VerifyPayment payment = new VerifyPayment(new Paypal("nonexistantjim@hotmail.com", "hunter2"));
        assertFalse(payment.isVerified());
    }

    @Test
    void assertVippsTlfnrPinVerified() {

        //checks that a valid tlfnr pincode combination is verified
        VerifyPayment payment = new VerifyPayment(new Vipps("12345678", "1234"));
        assertTrue(payment.isVerified());
    }

    @Test
    void assertVippsPinIncorrect() {

        //checks that a valid tlfnr with an invalid pincode returns false
        VerifyPayment payment = new VerifyPayment(new Vipps("12345678", "1111"));
        assertFalse(payment.isVerified());
    }

    @Test
    void assertVippsTlfnrNotInHashmap() {

        //checks that non-existant tlfnrs returns false
        VerifyPayment payment = new VerifyPayment(new Vipps("42424242", "4242"));
        assertFalse(payment.isVerified());
    }

    @Test
    void assertCardNumberCCVAndDateMatch() {

        //checks that a valid card number ccv combination is verified
        VerifyPayment payment = new VerifyPayment(new CreditDebit("1234234534564567", "123", 12, 2030));
        assertTrue(payment.isVerified());
    }

    @Test
    void assertOldDateIsInvalid() {

        //checks that a valid card number ccv combination with an expired date is rejected
        VerifyPayment payment = new VerifyPayment(new CreditDebit("1234234534564567", "123", 12, 1990));
        assertFalse(payment.isVerified());
    }

    @Test
    void assertCardNumberNotInHashmap() {

        //checks that an invalid card number is rejected
        VerifyPayment payment = new VerifyPayment(new CreditDebit("9999777788885555", "123", 12, 2030));
        assertFalse(payment.isVerified());
    }

    @Test
    void assertCardNumberInHashmapButInvalidCCV() {

        //checks that a valid card number but invalid ccv combination is rejected
        VerifyPayment payment = new VerifyPayment(new CreditDebit("1234234534564567", "999", 12, 2030));
        assertFalse(payment.isVerified());
    }



}