package no.hiof.groupproject.interfaces;

public interface ExistsInDb {

    //used to check if the email is stored in the database already. Returns true if the email is present
    boolean existsInDb();
}
