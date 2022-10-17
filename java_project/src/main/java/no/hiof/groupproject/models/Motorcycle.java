package no.hiof.groupproject.models;

public class Motorcycle extends Vehicle{
    private String helmetProvided;

    public Motorcycle(String ID, String regNumber, String manufacturer, String model,
                      String fuelType, String gearType, int modelYear, int price,
                      User owner, String helmetProvided) {

        super(ID, regNumber, manufacturer, model, fuelType, gearType, modelYear, price, owner);
        this.helmetProvided = helmetProvided;
    }


    public String getHelmetProvided() {
        return helmetProvided;
    }

    public void setHelmetProvided(String helmetProvided) {
        this.helmetProvided = helmetProvided;
    }
}
