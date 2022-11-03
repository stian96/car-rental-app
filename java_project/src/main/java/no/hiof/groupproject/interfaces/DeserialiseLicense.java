package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.License;
import no.hiof.groupproject.tools.db.RetrieveLicenseDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;

public interface DeserialiseLicense {
    //returns a License based on a specific licenseNumber primary key
    static License retrieveFromLicenseNr(String licenseNumber) {
        return RetrieveLicenseDB.retrieveFromLicenseNr(licenseNumber);
    }
    static License retrieveFromId(int id) {
        return RetrieveLicenseDB.retrieveFromId(id);
    }

}
