package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.Advertisement;

import java.time.LocalDate;
import java.util.Map;

public interface ExistsInDb {

    //used to check if the email is stored in the database already. Returns true if the email is present
    boolean existsInDb();
}
