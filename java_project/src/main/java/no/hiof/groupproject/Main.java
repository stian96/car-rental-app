package no.hiof.groupproject;


import no.hiof.groupproject.interfaces.*;
import no.hiof.groupproject.models.*;
import no.hiof.groupproject.models.payment_methods.Vipps;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.*;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        for (Advertisement advertisement : FilterAdvertisement.filterToArrayListAdvertisement(null, null,
                null, null,null, null, null,
                null, null, null)) {
            System.out.println(advertisement);
        }
        //the following code is used to test serialisation and deserialisation of the database
        //                             *********** IMPORTANT ***********
        //SQLite errors in the console is just feedback - often preventing duplicate entries (e.g UNIQUE constraints)
        //                             *********************************


        //kode til stian som kan brukes for å vise resultater
        HashMap<Integer, String> thing = new HashMap<>();
        ArrayList<Integer> thingWithInts = FilterAdvertisement.filterToArrayListAdvertisementId(null, null, null, "sarpsborg", null, null, null, null, null, null);

        for (int i : thingWithInts) {
            String sql = "SELECT * FROM advertisements INNER JOIN vehicles ON vehicle_fk = vehicles_id WHERE advertisements_id = " + i;
            String string = null;
            try (Connection conn = ConnectDB.connect();
                 PreparedStatement str = conn.prepareStatement(sql)) {

                ResultSet queryResult = str.executeQuery();

                    string = queryResult.getString("modelYear") + " " +
                            queryResult.getString("manufacturer") + " " +
                            queryResult.getString("model") + " - " +
                            queryResult.getString("town");

                } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            thing.put(i, string);
        }

        for (Map.Entry<Integer, String> result : thing.entrySet()) {
            System.out.println("\n\n" + result.getKey() + "     " + result.getValue() + "\n\n");
        }



        /*
        for (Vehicle vehicle : FilterAdvertisement.filterToArrayListVehicle(null, null,
                null, null,null, null, null,
                null, null, 1)) {
            System.out.println(vehicle);
        }

        License license = new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
                "Norway");

        User user = new User("sam", "davies", "1111", "hunter2",
                "12341234123", "sam@sam.no", "12341234", license);

        User user3 = new User("gsdfg", "qweqwe", "1111", "hunter2",
                "12341234123", "sam@samland.no", "12341234", license);
        User user4 = new User("wwww", "test", "1111", "hunter2",
                "12341234123", "test@test.no", "12341234", license);

        Vehicle car = new Car("12341234", "audi", "tt", "petrol",
                "automatic", 2013, 5, 1500);

        RentOutAd roa = new RentOutAd(
                user,
                car,
                BigDecimal.valueOf(200), BigDecimal.valueOf(10), "Sarpsborg"
                );

        System.out.println(LocalDate.parse("2022-08-01"));
        roa.addNewPeriod(LocalDate.parse("2022-08-01"), LocalDate.parse("2022-12-23"));
        roa.addNewPeriod(LocalDate.parse("2022-12-26"), LocalDate.parse("2023-12-23"));
        roa.addBooking(new Booking(user, user3, LocalDate.parse("2022-11-10"), LocalDate.parse("2022-11-15"),
                new Vipps("12345678", "1234"), car));
        roa.addBooking(new Booking(user, user3, LocalDate.parse("2022-11-16"), LocalDate.parse("2022-11-17"),
                new Vipps("12345678", "1234"), car));

        TreeMap<LocalDate, LocalDate> testing = new TreeMap<>();

        testing.put(LocalDate.parse("2022-08-01"), LocalDate.parse("2022-12-23"));

        License lic2 = DeserialiseLicense.deserialiseFromLicenseNr("98 45 123456 1");
        System.out.println(lic2.getCountryOfIssue());

        UserProfile up = new UserProfile(user);
        up.addNewRating(user4, 5);
        up.addNewRating(user3, 4);
        HashMap<User, Integer> ratings = DeserialiseRating.deserialise(up);
        for (Map.Entry<User, Integer> rating : ratings.entrySet()) {
            System.out.println("\n\nUser giving ratings: " + rating.getKey().getFirstName() + " " +
                    rating.getKey().getLastName() + "\nRating score: " + rating.getValue());
        }

        System.out.println(up.getUser().getFirstName() + " " + up.getUser().getLastName() + " has an average rating " +
                "of " + up.getAverageRating());


        System.out.println(roa.getLocation().getThisLocationInfo());
        System.out.println(roa.getLocation().getBy() + " is in the county of "
                + roa.getLocation().getFylke() + " with a postcode of "
                + roa.getLocation().getPostNr() + " in the country "
                + roa.getLocation().getLand());

        System.out.println(RetrieveBookingsDB.retrieve(roa));

        User user5 = new User("testing@this.email", "breadismyfavouritefood");
        User user6 = new User("testing@updating.fromhere", "breadismyfavouritefood");
        User user7 = new User("ronny", "pickering", "1777", user6.getPassword(), "12341234123", user6.getEmail(), "12341234", lic2);

        User user8 = new User("blahblah@test.no", "catsanddogs");

        RentOutAd roaVehicle2 = new RentOutAd(
                user7,
                new Car("11223344", "aston martin", "db5", "petrol",
                        "manual", 1963, 2, 400),
                BigDecimal.valueOf(400), BigDecimal.valueOf(30), "halden"
        );

        System.out.println(roa.checkIfDateIsAvailable(LocalDate.parse("2022-11-16"), LocalDate.parse("2022-11-17")));
        System.out.println(roa.checkIfDateIsAvailable(LocalDate.parse("2022-11-20"), LocalDate.parse("2022-11-22")));
         */

    }
}
