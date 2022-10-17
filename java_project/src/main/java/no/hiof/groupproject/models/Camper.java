package no.hiof.groupproject.models;


public class Camper extends Vehicle {
    private int seatingCapacity, towingAbility;
    private boolean includesToilet, includesBed, includesKitchen;
    /* check if the camper includes the above if true
     * there should be additional rates */

    public Camper(String ID, String regNumber, String manufacturer, String model, String fuelType,
                  String gearType, int modelYear, int price, User owner){
        super(ID, regNumber, manufacturer, model, fuelType, gearType, modelYear, price, owner);
    }

}