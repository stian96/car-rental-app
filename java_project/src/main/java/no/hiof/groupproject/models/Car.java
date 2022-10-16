package no.hiof.groupproject.models;

public class Car extends Vehicle{
    private int seatingCapacity;
    private int towingAbility;

    public Car(String ID, String regNumber, String manufacturer, String model,
               String fuelType, String gearBox, int modelYear, int price, User owner,
               int vehicleStatus, int seatingCapacity, int towingAbility) {

        super(ID, regNumber, manufacturer, model, fuelType, gearBox, modelYear, price, owner, vehicleStatus);
        this.seatingCapacity = seatingCapacity;
        this.towingAbility = towingAbility;

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
}
