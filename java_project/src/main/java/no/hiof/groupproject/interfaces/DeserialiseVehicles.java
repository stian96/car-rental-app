package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;

public interface DeserialiseVehicles {
    //returns a Vehicle based on a specific users_id primary key
    static Vehicle deserialiseSpecificId(int id) {
        return RetrieveVehicleDB.retrieveFromId(id);
    }

    //returns a Vehicle based on a specific regNo
    static Vehicle deserialiseSpecificEmail(String regNo) {
        return RetrieveVehicleDB.retrieveFromRegNo(regNo);
    }
}
