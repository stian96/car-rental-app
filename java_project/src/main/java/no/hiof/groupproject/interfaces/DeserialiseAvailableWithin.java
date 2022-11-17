package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.tools.db.RetrieveAvailableWithinDB;

import java.time.LocalDate;
import java.util.TreeMap;

//returns the valid rental periods in the database based on a specific RentOutAd
public interface DeserialiseAvailableWithin {


    static TreeMap<LocalDate, LocalDate> deserialise(RentOutAd rentOutAd) {
        return RetrieveAvailableWithinDB.retrieve(rentOutAd);
    }

}
