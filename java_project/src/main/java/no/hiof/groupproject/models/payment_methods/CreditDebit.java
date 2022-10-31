package no.hiof.groupproject.models.payment_methods;

import java.time.LocalDate;

public class CreditDebit extends Payment {

    //string for these details to account for a 0 appearing
    private String card_number, ccv;
    //expired cards can still be instantiated
    //VerifyPayment class actually checks that the date is valid as of current date
    private LocalDate valid_until;
    private CreditDebitPair pair;

    public CreditDebit(String card_number, String ccv, int month, int year) {

        //removes spaces and characters such as \n
        card_number = card_number.replaceAll("\\s+", "");

        //checks that card_number length is 16, ccv is 3, year is 4, and month is 1 or 2
        if (!checkLength(card_number, 16)
                || !checkLength(ccv, 3)
                || !checkLength(Integer.toString(year), 4)
                ||!(checkLength(Integer.toString(month), 1) || checkLength(Integer.toString(month), 2))) {
            throw new IllegalArgumentException();
        }
        else if (month == 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.card_number = card_number;
            this.ccv = ccv;
            //expires the first day of the month
            valid_until = LocalDate.of(year, month, 1);
            //creates a string string pair object for use in testing hashmap
            //and database storage
            storeCCVDateInPair();
        }
    }

    //creates a pair that can be inserted as a value so that the dummy
    //hashmap used for testing has 3 values instead of 2
    public void storeCCVDateInPair() {
        pair = new CreditDebitPair(this.ccv, this.valid_until);
    }

    public CreditDebitPair getPair() {
        return pair;
    }

    public void setPair(CreditDebitPair pair) {
        this.pair = pair;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
        //updates pair as well
        this.pair.setCcv(ccv);
    }

    public LocalDate getValid_until() {
        return valid_until;
    }

    public void setValid_until(LocalDate valid_until) {
        this.valid_until = valid_until;
        //updates pair as well
        this.pair.setValid_until(valid_until);
    }
}
