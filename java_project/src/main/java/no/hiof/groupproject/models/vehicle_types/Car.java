package no.hiof.groupproject.models.vehicle_types;

public class Car extends Vehicle {
    private int seatingCapacity;
    private int towingCapacity;

    public Car(String regNumber, String manufacturer, String model,
               String fuelType, String gearType, int modelYear,
               int seatingCapacity, int towingCapacity) {

        super(regNumber, manufacturer, model, fuelType, gearType, modelYear);
        this.seatingCapacity = seatingCapacity;
        this.towingCapacity = towingCapacity;
        super.setVehicleSubclass("car");
        serialise();

    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public int getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(int towingCapacity) {
        this.towingCapacity = towingCapacity;
    }
}
