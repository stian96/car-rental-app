package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.tools.db.RetrieveAvailableWithinDB;
import no.hiof.groupproject.tools.db.RetrieveAverageRatingDB;
import no.hiof.groupproject.tools.db.RetrieveRatingDB;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

//returns the valid rental periods in the database based on a specific RentOutAd
public interface DeserialiseAvailableWithin {


    static TreeMap<LocalDate, LocalDate> deserialise(RentOutAd rentOutAd) {
        return RetrieveAvailableWithinDB.retrieve(rentOutAd);
    }

}
