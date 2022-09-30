package no.hiof.groupproject.models;

public class RentOutAd extends Advertisement{
    /*This page is for the owner to rent out the car*/
    private Vehicle vehicle;
    private int rate;//not sure where this should be here or car
    public RentOutAd(String id, Vehicle vehicle, int rate){
        super();
        this.vehicle = vehicle;
        this.rate = rate;
    }
}
