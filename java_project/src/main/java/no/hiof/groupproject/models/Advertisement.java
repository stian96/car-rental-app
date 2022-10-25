package no.hiof.groupproject.models;

import java.time.LocalDate;

/*
An abstract class that is used for both advertisements relating to renting out a vehicle, but also requests
for a specific vehicle or date.
 */

public abstract class Advertisement {

    //auto-incremental id
    private static int count = 1;
    private int id;

    private User user;
    //date that the advertisement was created
    private LocalDate dateCreated;
    //date that the advertisement was last edited
    private LocalDate dateLastChanged;

    public Advertisement(int id, User user, LocalDate dateCreated, LocalDate dateLastChanged) {
        this.id = count;
        //increments the id by 1
        count++;
        this.user = user;
        this.dateCreated = dateCreated;
        this.dateLastChanged = dateLastChanged;
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
}
