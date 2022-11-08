package no.hiof.groupproject.tools.filters;

import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.util.ArrayList;
import java.util.Objects;

/*
Class designed to return ArrayList<Vehicle> from a supplied ArrayList<Vehicle> and a parameter
 */
public class FilterVehicle {

    // Example of usage:
    // FilterVehicle.filterGearType(RetrieveVehiclesDB.retrieveAllVehiclesObject(), "manual")
    //returns an ArrayList<Vehicle> of all vehicles with a "manual" gear type based on the ArrayList given
    public static ArrayList<Vehicle> filterGearType(ArrayList<Vehicle> vehicles, String choice) {

        ArrayList<Vehicle> returnedList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getGearType(), choice)) {
                returnedList.add(vehicle);
            }
        }
        return returnedList;
    }
}
