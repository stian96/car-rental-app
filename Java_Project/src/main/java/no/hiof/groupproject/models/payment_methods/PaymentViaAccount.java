package no.hiof.groupproject.models.payment_methods;

import no.hiof.groupproject.models.payment_methods.Payment;

public abstract class PaymentViaAccount extends Payment {

    private String email;
    private String pwd;

    //validating emails isn't necessary at the prototype stage
    //more efficient to just assume that the email exists as long as it
    //contains an @ symbol
    public PaymentViaAccount (String email, String pwd) {

        if (email.indexOf('@') != -1 && pwd.length() > 0) {
            this.email = email;
            this.pwd = pwd;
        }
        else {
            throw new IllegalArgumentException();
        }

    }

    public PaymentViaAccount() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
