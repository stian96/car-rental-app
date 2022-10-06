package no.hiof.groupproject.models.payment_methods;

import no.hiof.groupproject.models.payment_methods.Payment;

public class Vipps extends Payment {

    //string for these details to account for a 0 appearing
    String tlfnr;
    String pincode;

    public Vipps(String tlfnr, String pincode) {

        //removes all spaces and characters such as \n
        tlfnr = tlfnr.replaceAll("\\s+", "");
        pincode = pincode.replaceAll("\\s+", "");

        if (!checkLength(pincode, 4)) {
            throw new IllegalArgumentException();
        }
        else if (tlfnr.length() < 8) {
            throw new IllegalArgumentException();
        }
        else {
            this.tlfnr = tlfnr;
            this.pincode = pincode;
        }

    }

    public Vipps() {

    }
}
