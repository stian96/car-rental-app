package no.hiof.groupproject.models;

import java.util.ArrayList;

public class UserProfile {

    //variables should be User, array of Advertisements, rating (together with a system for averaging ratings),
    //display picture, description
    private User user;
    private ArrayList<Advertisement> advertisements;

    //Will need ratings might be a class of its own?
    //bio field

    public UserProfile() {
        this.advertisements = new ArrayList<>();
    }

    public void AddAdvertisement(Advertisement advertisement) {
        advertisements.add(advertisement);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(ArrayList<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
