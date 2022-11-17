package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Advertisement;

import java.sql.*;
import java.util.ArrayList;

/*
Returns an ArrayList of Advertisements or Advertisement id based off a User id.
Useful for allowing a single UserProfile to have multiple vehicles
 */
public class RetrieveAdvertisementsDB {

    public static ArrayList<Advertisement> retrieveAdvertisementsObjectFromUserId(int id) {

        String sql = "SELECT * FROM advertisements WHERE user_fk = " + id;

        ArrayList<Advertisement> returnedAdvertisements = new ArrayList<>();

        try (Connection conn = ConnectDB.connectReadOnly();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {



            //loops through rows in the sql SELECT statement
            while (rs.next()) {

                Advertisement advertisement =  RetrieveAdvertisementDB.
                        retrieveFromId(rs.getInt("advertisements_id"));
                returnedAdvertisements.add(advertisement);
            }

            return returnedAdvertisements;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return returnedAdvertisements;

    }

   /* public static ArrayList<Advertisement> retrieveAdvertisementObjectFromIdWithPriorConnection(int id, Connection conn) throws SQLException {

        String sql = "SELECT * FROM advertisements WHERE user_fk = " + id;

        ArrayList<Advertisement> returnedAdvertisements = new ArrayList<>();

        Statement str = conn.createStatement();
        ResultSet rs = str.executeQuery(sql);

        //loops through rows in the sql SELECT statement
        while (rs.next()) {

            Advertisement advertisement =  RetrieveAdvertisementDB.retrieveFromId(rs.getInt("advertisements_id"));
            returnedAdvertisements.add(advertisement);
        }


        return returnedAdvertisements;
    }*/

    public static ArrayList<Integer> retrieveAdvertisementsIdFromUserId(int id) {

        String sql = "SELECT * FROM advertisements WHERE user_fk = " + id;

        ArrayList<Integer> returnedAdvertisements = new ArrayList<>();


        try (Connection conn = ConnectDB.connectReadOnly();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {



            //loops through rows in the sql SELECT statement
            while (rs.next()) {

                int advertisementId = rs.getInt("advertisements_id");
                returnedAdvertisements.add(advertisementId);
            }

            return returnedAdvertisements;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return returnedAdvertisements;

    }

/*
    public static ArrayList<Integer> retrieveAdvertisementIdFromIdWithPriorConnection(int id, Connection conn) throws SQLException {

        String sql = "SELECT * FROM advertisements WHERE user_fk = " + id;

        ArrayList<Integer> returnedAdvertisements = new ArrayList<>();

        Statement str = conn.createStatement();
        ResultSet rs = str.executeQuery(sql);

        //loops through rows in the sql SELECT statement
        while (rs.next()) {

            int advertisementId = rs.getInt("advertisements_id");
            returnedAdvertisements.add(advertisementId);
        }


        return returnedAdvertisements;
    }
*/

}