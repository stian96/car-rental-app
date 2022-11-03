package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;

//returns an Advertisement based on a specific advertisements_id value
public interface DeserialiseAdvertisement {

    static Advertisement deserialise(int id) {
        return RetrieveAdvertisementDB.retrieveFromId(id);
    }

}
