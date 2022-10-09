package no.hiof.groupproject.models.payment_methods;

import java.time.LocalDate;

//this class exists to create a hashmap with the format <String <String, LocalDate>> (1 key with 2 values)
//this class
public class CreditDebitPair {

    private String ccv;
    private LocalDate valid_until;

    public CreditDebitPair(String ccv, LocalDate valid_until) {
        this.ccv = ccv;
        this.valid_until = valid_until;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public LocalDate getValid_until() {
        return valid_until;
    }

    public void setValid_until(LocalDate valid_until) {
        this.valid_until = valid_until;
    }
}
