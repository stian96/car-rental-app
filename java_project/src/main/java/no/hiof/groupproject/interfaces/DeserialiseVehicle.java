package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;
import no.hiof.groupproject.tools.db.RetrieveVehiclesDB;

import java.util.ArrayList;
import java.util.HashMap;

public interface DeserialiseVehicle {
    //returns an ArrayList of all Vehicle ids
    static ArrayList<Integer> deserialiseAllVehiclesId() {
        return RetrieveVehiclesDB.retrieveAllVehiclesId();
    }

    //returns an ArrayList of all Vehicles
    static ArrayList<Vehicle> deserialiseAllVehiclesObject() {
        return RetrieveVehiclesDB.retrieveAllVehiclesObject();
    }

    //returns a Hashmap of all Advertisements with corresponding Vehicles
    static HashMap<Advertisement, Vehicle> deserialiseAllVehiclesAndAdvertisementsObject() {
        return RetrieveVehiclesDB.retrieveAllVehiclesAndAdvertisementsObject();
    }

    //returns a Hashmap of all Advertisement ids with corresponding Vehicle ids
    static HashMap<Integer, Integer> deserialiseAllVehiclesAndAdvertisementsId() {
        return RetrieveVehiclesDB.retrieveAllVehiclesAndAdvertisementsId();
    }
}
