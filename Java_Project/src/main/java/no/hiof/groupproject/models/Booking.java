package no.hiof.groupproject.models;

import no.hiof.groupproject.models.payment_methods.Payment;

import java.util.Date;

public class Booking {
    /*This class is for booking the vehicle*/
    private int id;
    private Advertisement ad; //should it be ad or car?
    private Customer customer;
    private Date dateFrom; //will need to convert to string for retrieval
    private Date dateTil;
    private Payment payment;

}
