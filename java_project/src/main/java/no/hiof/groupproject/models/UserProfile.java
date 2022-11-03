package no.hiof.groupproject.models;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.InsertRatingDB;

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
    private ArrayList<Advertisement> advertisements;
    private HashMap<User, Integer> ratings; // Hashmap that stores all the users and their ratings of current user
    private double averageRating = 0; //Current user's avg rating

    public UserProfile(User user) {
        this.user = user;
        advertisements = new ArrayList<>();
        ratings = new HashMap<>();

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
     * @param userGivingRating checks if user is in the hashmap already.
     * @param rating new rating to place in the hashmap.
     * @return average of the total ratings
     */
    public double addNewRating(User userGivingRating, Integer rating){
        //this.user cannot give themselves a rating
        if (!ratingExistsInDb(userGivingRating) && userGivingRating.getId() != user.getId()){
            //if the userGivingRating hasn't rated user before then the following code is executed
            ratings.put(userGivingRating, rating);
            InsertRatingDB.insert(user, userGivingRating, rating);
            return calculateAverageRating(ratings);
        } else if (ratingExistsInDb(userGivingRating) && userGivingRating.getId() != user.getId()){
            //if the userGivingRating wishes to update their rating:
            InsertRatingDB.update(user, userGivingRating, rating);
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

    //required to update existing ratings in the database
    public boolean ratingExistsInDb(User userGivingRating) {
        String sql = "SELECT COUNT(*) AS amount FROM ratings WHERE userGivingRating = " +
                userGivingRating.getId() + " AND user = " + user.getId();

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
