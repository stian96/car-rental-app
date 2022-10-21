package no.hiof.groupproject.models.vehicle_types;

import no.hiof.groupproject.models.User;

public class Car extends Vehicle {
    private int seatingCapacity;
    private int towingAbility;
    private String carType;

    public Car(String ID, String regNumber, String manufacturer, String model,
               String fuelType, String gearBox, int modelYear, User owner,
               int vehicleStatus, int seatingCapacity, int towingAbility, String carType) {

        super(ID, regNumber, manufacturer, model, fuelType, gearBox, modelYear, owner);
        this.seatingCapacity = seatingCapacity;
        this.towingAbility = towingAbility;
        this.carType = carType;

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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
