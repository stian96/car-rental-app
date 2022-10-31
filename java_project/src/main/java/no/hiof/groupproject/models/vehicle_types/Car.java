package no.hiof.groupproject.models.vehicle_types;

public class Car extends Vehicle {
    private int seatingCapacity;
    private int towingCapacity;

    public Car(String regNumber, String manufacturer, String model,
               String fuelType, String gearBox, int modelYear,
               int seatingCapacity, int towingCapacity) {

        super(regNumber, manufacturer, model, fuelType, gearBox, modelYear);
        this.seatingCapacity = seatingCapacity;
        this.towingCapacity = towingCapacity;
        super.setVehicleSubclass("car");

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
