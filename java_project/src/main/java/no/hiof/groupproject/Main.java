package no.hiof.groupproject;


import no.hiof.groupproject.interfaces.DeserialiseUser;
import no.hiof.groupproject.interfaces.DeserialiseVehicle;
import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.payment_methods.Vipps;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.VerifyLicense;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {


        //testing instantiation of rentOutAd using dependency injection
        /*
        GenericQueryDB.query("INSERT INTO people(name,age) VALUES('jesper', 14)");
        */


        VerifyLicense license = new VerifyLicense("98 45 123456 1", LocalDate.parse("2008-05-12"),
                "Norway");

        User user = new User("sam", "davies", "1111", "hunter2",
                "12341234123", "sam@sam.no", "12341234", license);
        User user3 = new User("gsdfg", "qweqwe", "1111", "hunter2",
                "12341234123", "sam@samland.no", "12341234", license);


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

        TreeMap<LocalDate, LocalDate> testing = new TreeMap<>();

        testing.put(LocalDate.parse("2022-08-01"), LocalDate.parse("2022-12-23"));



        System.out.println(roa.getDateCreated().toString());
        System.out.println("\n\n\n" + user.getAutoIncrementId());
        System.out.println("\n\n\n" + car.getAutoIncrementId());

        Vipps blah = new Vipps("55664477", "5435");
        System.out.println(blah.getId());

        Paypal blah2 = new Paypal("sam@sam.eg", "asdawfw");
        System.out.println(blah2.getId());



        /*
        System.out.println(roa.getLocation().getThisLocationInfo());
        System.out.println(roa.getLocation().getBy() + " is in the county of "
                + roa.getLocation().getFylke() + " with a postcode of "
                + roa.getLocation().getPostNr() + " in the country "
                + roa.getLocation().getLand());

         */





        //serialisation test
        /*
        Configuration factory = new Configuration();
        factory.configure("hibernate.cfg.xml");
         */


    }
}
