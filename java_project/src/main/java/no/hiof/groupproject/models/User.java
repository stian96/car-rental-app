package no.hiof.groupproject.models;

public class User {

    private int id;

    //integration of a driving license class here would be great - so that VerifyDrivingLicense could be used when
    //a GUI button to rent a car is activated
    //private DrivingLicence dLicense:


    private String firstName, lastName, postNr;
    //obviously the password would be encrypted in a final build
    private String password;
    //relevant for receiving payment via card, paypal, vipps etc.
    private String bankAccountNr, email, tlfNr;



}
