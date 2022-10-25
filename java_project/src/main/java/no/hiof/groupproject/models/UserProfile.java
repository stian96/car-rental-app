package no.hiof.groupproject.models;

import java.util.ArrayList;
import java.util.HashMap;

public class UserProfile {


    //variables should be User, array of Advertisements, rating (together with a system for averaging ratings),
    //display picture, description
    private User user;
    private ArrayList<Advertisement> advertisements;
    private HashMap<User, Integer> ratings = new HashMap<>(); // Hashmap that stores all the users and their ratings of current user
    private double averageRating = 0; //Current user's avg rating

    /** Method to calculates the average rating for the User*/
    public double calculateAverageRating(HashMap<User, Integer> ratings){
        double sum = 0;
        int count = ratings.size();
        double result;
        for(int values : ratings.values()){
            sum += values;
            result = sum/((double) count);//Fix later : round to one or two decimal places
            averageRating = result;
        }
        return averageRating;
    }

    /** Method that gets new rating to add to the ratings hashmap.
     *
     * @param user checks if user is in the hashmap already.
     * @param rate new rating to place in the hashmap.
     * @return average of the total ratings
     */
    public  double addNewRating(User user ,Integer rate){
        if(!ratings.containsKey(user)){
            ratings.put(user,rate);
            return calculateAverageRating(ratings);
        }
        return 0;
    }

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
