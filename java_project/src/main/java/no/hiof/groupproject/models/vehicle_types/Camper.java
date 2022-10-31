package no.hiof.groupproject.models.vehicle_types;

import no.hiof.groupproject.models.User;


public class Camper extends Vehicle {
    private int seatingCapacity, towingAbility, bedCapacity, dimensions;
    private boolean includesToilet, includesKitchen;
    /* check if the camper includes the above if true
     * there should be additional rates */

    public Camper(String regNumber, String manufacturer, String model, String engineType, String gearType,
                  int modelYear, int seatingCapacity, int towingAbility, int bedCapacity, int dimensions,
                  boolean includesToilet, boolean includesKitchen) {
        super(regNumber, manufacturer, model, engineType, gearType, modelYear);
        this.seatingCapacity = seatingCapacity;
        this.towingAbility = towingAbility;
        this.bedCapacity = bedCapacity;
        this.dimensions = dimensions;
        this.includesToilet = includesToilet;
        this.includesKitchen = includesKitchen;
        super.setVehicleSubclass("camper");
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public int getTowingAbility() {
        return towingAbility;
    }

    public void setTowingAbility(int towingAbility) {
        this.towingAbility = towingAbility;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public boolean isIncludesToilet() {
        return includesToilet;
    }

    public void setIncludesToilet(boolean includesToilet) {
        this.includesToilet = includesToilet;
    }

    public boolean isIncludesKitchen() {
        return includesKitchen;
    }

    public void setIncludesKitchen(boolean includesKitchen) {
        this.includesKitchen = includesKitchen;
    }
}