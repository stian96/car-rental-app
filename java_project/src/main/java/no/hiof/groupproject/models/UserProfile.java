package no.hiof.groupproject.models;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.tools.db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserProfile implements Serialise, ExistsInDb {


    //variables should be User, array of Advertisements, rating (together with a system for averaging ratings),
    //display picture, description
    //Will need ratings might be a class of its own?
    //bio field
    private User user;
    private ArrayList<Advertisement> advertisements = new ArrayList<>();
    private HashMap<User, Integer> ratings = new HashMap<>(); // Hashmap that stores all the users and their ratings of current user
    private double averageRating = 0; //Current user's avg rating

    public UserProfile(User user) {
        this.user = user;

    }

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
    public double addNewRating(User user ,Integer rate){
        if(!ratings.containsKey(user)){
            ratings.put(user,rate);
            return calculateAverageRating(ratings);
        }
        return 0;
    }

    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM userProfiles WHERE user_fk = " + this.user.getId();

        boolean ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void serialise() {

    }

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
