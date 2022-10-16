package no.hiof.groupproject.models;

public class Moped extends Vehicle{
    private String helmetProvided;

    public Moped(String ID, String regNumber, String manufacturer, String model,
                 String fuelType, String gearType, int modelYear, int price,
                 User owner, int vehicleStatus, String helmetProvided) {

        super(ID, regNumber, manufacturer, model, fuelType, gearType, modelYear, price, owner, vehicleStatus);
        this.helmetProvided = helmetProvided;

    }


    public String isHelmetProvided() {
        return helmetProvided;
    }

    public void setHelmetProvided(String helmetProvided) {
        this.helmetProvided = helmetProvided;
    }
}
