package no.hiof.groupproject.models.vehicles.two_wheeled_vehicles;

import no.hiof.groupproject.models.vehicles.Vehicle;

public class Moped extends Vehicle {
    private String helmetProvided;

    public Moped(String regNumber, String manufacturer, String model,
                 String fuelType, String gearType, int modelYear,
                 String helmetProvided) {

        super(regNumber, manufacturer, model, fuelType, gearType, modelYear);
        this.helmetProvided = helmetProvided;
        super.setVehicleSubclass("moped");

        if (!existsInDb()) {
            serialise();
        }
        //the id is automatically incremented when inserted into the database
        //the autoincrement id is fetched and assigned to this instance
        this.setId(getAutoIncrementId());
    }


    public String getHelmetProvided() {
        return helmetProvided;
    }

    public void setHelmetProvided(String helmetProvided) {
        this.helmetProvided = helmetProvided;
    }
}
