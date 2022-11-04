package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import no.hiof.groupproject.tools.db.RetrieveUserProfileDB;

public interface DeserialiseUserProfile {
    //returns a UserProfile based on a specific User id
    static UserProfile deserialise(int id) {
        return RetrieveUserProfileDB.retrieve(id);
    }

}
