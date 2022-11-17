package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.tools.db.RetrieveBookingDB;
import no.hiof.groupproject.tools.db.RetrieveBookingsDB;

import java.util.ArrayList;

public interface DeserialiseBooking {
    //returns either an averageRating in String format or a HashMap of all ratings based on a specific userProfile
    static Booking deserialiseSingleBooking(String strId) {
        return RetrieveBookingDB.retrieve(strId);
    }
    static ArrayList<Booking> deserialiseBookingsList(RentOutAd rentOutAd) {
        return RetrieveBookingsDB.retrieve(rentOutAd);
    }

}
