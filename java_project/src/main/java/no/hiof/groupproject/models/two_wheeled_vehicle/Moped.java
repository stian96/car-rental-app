package no.hiof.groupproject.models.two_wheeled_vehicle;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

public class Moped extends Vehicle {
    private String helmetProvided;

    public Moped(String regNumber, String manufacturer, String model,
                 String fuelType, String gearType, int modelYear,
                 String helmetProvided) {

        super(regNumber, manufacturer, model, fuelType, gearType, modelYear);
        this.helmetProvided = helmetProvided;

    }


    public String isHelmetProvided() {
        return helmetProvided;
    }

    public void setHelmetProvided(String helmetProvided) {
        this.helmetProvided = helmetProvided;
    }
}
