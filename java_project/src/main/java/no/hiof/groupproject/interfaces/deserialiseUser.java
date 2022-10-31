package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.RetrieveUserDB;

public interface deserialiseUser {
    //returns a User based on a specific users_id primary key
    static User deserialiseSpecificId(int id) {
        return RetrieveUserDB.retrieveFromId(id);
    }

    //returns a User based on a specific email
    static User deserialiseSpecificEmail(String email) {
        return RetrieveUserDB.retrieveFromEmail(email);
    }
}
