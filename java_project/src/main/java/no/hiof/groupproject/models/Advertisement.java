package no.hiof.groupproject.models;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.GetAutoIncrementId;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.tools.db.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
An abstract class that is used for both advertisements relating to renting out a vehicle, but also requests
for a specific vehicle or date.
 */

public abstract class Advertisement implements Serialise, GetAutoIncrementId, ExistsInDb {

    //auto-incremental id
    //private static int count = 1;
    private int id;

    private User user;
    //date that the advertisement was created
    private LocalDate dateCreated;
    //date that the advertisement was last edited
    private LocalDate dateLastChanged;
    private String advertisementSubclass;

    public Advertisement(User user) {
        //this.id = count;
        //increments the id by 1
        //count++;
        this.user = user;
        this.dateCreated = LocalDate.now();
        updateDateLastChanged();
    }

    @Override
    public void serialise() {
        InsertAdvertisementDB.insert(this);
    }

    // used when data is changed/updated
    public void updateDateLastChanged() {
        this.dateLastChanged = LocalDate.now();
        //updates the database at the same time
        GenericQueryDB.query("UPDATE advertisements SET dateLastChanged = " + this.dateLastChanged.toString() +
                " WHERE advertisements_id = " + this.id);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateLastChanged() {
        return dateLastChanged;
    }

    public void setDateLastChanged(LocalDate dateLastChanged) {
        this.dateLastChanged = dateLastChanged;
    }

    public String getAdvertisementSubclass() {
        return advertisementSubclass;
    }

    public void setAdvertisementSubclass(String advertisementSubclass) {
        this.advertisementSubclass = advertisementSubclass;
    }
}
