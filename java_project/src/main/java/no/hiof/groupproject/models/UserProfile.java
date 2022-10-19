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

    /** Method that calculates the average rating for the User*/
    public double calculateAverageRating(HashMap<User, Integer> ratings){
        double sum = 0;
        int count = ratings.size();
        double result = 0;
        for(int values : ratings.values()){
            sum += values;
            result = sum/((double) count);
        }
        return result;
    }

    //Will need ratings might be a class of its own?
    //bio field
}
