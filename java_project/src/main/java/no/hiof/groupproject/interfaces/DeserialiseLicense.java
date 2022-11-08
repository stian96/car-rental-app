package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.License;
import no.hiof.groupproject.tools.db.RetrieveLicenseDB;

public interface DeserialiseLicense {
    //returns a License based on a specific licenseNumber primary key
    static License deserialiseFromLicenseNr(String licenseNumber) {
        return RetrieveLicenseDB.retrieveFromLicenseNr(licenseNumber);
    }
    static License deserialiseFromId(int id) {
        return RetrieveLicenseDB.retrieveFromId(id);
    }

}
