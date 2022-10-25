package no.hiof.groupproject.models.two_wheeled_vehicle;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

public class Motorcycle extends Vehicle {
    private String helmetProvided;

    public Motorcycle(String regNumber, String manufacturer, String model,
                      String fuelType, String gearType, int modelYear,
                      String helmetProvided) {

        super(regNumber, manufacturer, model, fuelType, gearType, modelYear);
        this.helmetProvided = helmetProvided;
    }


    public String getHelmetProvided() {
        return helmetProvided;
    }

    public void setHelmetProvided(String helmetProvided) {
        this.helmetProvided = helmetProvided;
    }
}
