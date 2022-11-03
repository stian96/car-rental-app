package no.hiof.groupproject;


import no.hiof.groupproject.interfaces.*;
import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.models.payment_methods.Vipps;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.License;
import no.hiof.groupproject.tools.db.RetrieveLicenseDB;
import no.hiof.groupproject.tools.db.RetrieveRatingDB;
import no.hiof.groupproject.tools.db.RetrieveUserProfileDB;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {


        //the following code is used to test serialisation and deserialisation of the database
        //                             *********** IMPORTANT ***********
        //SQLite errors in the console is just feedback - often preventing duplicate entries (e.g UNIQUE constraints)
        //                             *********************************
        License license = new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
                "Norway");

        User user = new User("sam", "davies", "1111", "hunter2",
                "12341234123", "sam@sam.no", "12341234", license);
        User user3 = new User("gsdfg", "qweqwe", "1111", "hunter2",
                "12341234123", "sam@samland.no", "12341234", license);
        User user4 = new User("wwww", "test", "1111", "hunter2",
                "12341234123", "test@test.no", "12341234", license);

        User user2 = DeserialiseUser.deserialiseSpecificId(1);
        System.out.println(user2.getFirstName() + " lives at " + user2.getPostNr() + " and has a license from " +
                user2.getdLicense().getCountryOfIssue());

        Vehicle car = new Car("12341234", "audi", "tt", "petrol",
                "automatic", 2013, 5, 1500);

        Vehicle car2 = DeserialiseVehicle.deserialiseSpecificId(1);
        System.out.println(car2.getRegNo() + " is a " + car2.getManufacturer() + " " + car2.getModel() + " from "
                + car2.getModelYear());

        RentOutAd roa = new RentOutAd(
                user,
                car,
                BigDecimal.valueOf(200), BigDecimal.valueOf(10), "Sarpsborg"
                );

        System.out.println(LocalDate.parse("2022-08-01"));
        roa.addNewPeriod(LocalDate.parse("2022-08-01"), LocalDate.parse("2022-12-23"));
        roa.addNewPeriod(LocalDate.parse("2022-12-26"), LocalDate.parse("2023-12-23"));
        roa.addBooking(new Booking(user, user3, LocalDate.parse("2022-11-10"), LocalDate.parse("2022-11-15"),
                new Vipps("12345678", "1234")));
        roa.addBooking(new Booking(user, user3, LocalDate.parse("2022-11-16"), LocalDate.parse("2022-11-17"),
                new Vipps("12345678", "1234")));

        TreeMap<LocalDate, LocalDate> testing = new TreeMap<>();

        testing.put(LocalDate.parse("2022-08-01"), LocalDate.parse("2022-12-23"));

        License lic2 = DeserialiseLicense.retrieveFromLicenseNr("98 45 123456 1");
        System.out.println(lic2.getCountryOfIssue());
        License lic3 = DeserialiseLicense.retrieveFromId(1);
        System.out.println(lic3.getCountryOfIssue());

        UserProfile up = new UserProfile(user);
        up.addNewRating(user4, 5);
        up.addNewRating(user3, 4);
        HashMap<User, Integer> ratings = DeserialiseRating.retrieve(up);
        for (Map.Entry<User, Integer> rating : ratings.entrySet()) {
            System.out.println("\n\nUser giving ratings: " + rating.getKey().getFirstName() + " " +
                    rating.getKey().getLastName() + "\nRating score: " + rating.getValue());
        }

        UserProfile up2 = DeserialiseUserProfile.deserialise(1);
        System.out.println(up2.getUser().getFirstName() + " " + up2.getUser().getLastName() + " has an average rating " +
                "of " + up2.getAverageRating());

        System.out.println(roa.getLocation().getThisLocationInfo());
        System.out.println(roa.getLocation().getBy() + " is in the county of "
                + roa.getLocation().getFylke() + " with a postcode of "
                + roa.getLocation().getPostNr() + " in the country "
                + roa.getLocation().getLand());


    }
}
