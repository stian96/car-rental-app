package no.hiof.groupproject;


import no.hiof.groupproject.interfaces.DeserialiseUser;
import no.hiof.groupproject.interfaces.DeserialiseVehicle;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        //testing instantiation of rentOutAd using dependency injection
        /*
        GenericQueryDB.query("INSERT INTO people(name,age) VALUES('jesper', 14)");
        */


        User user = new User("sam", "davies", "1111", "hunter2",
                "12341234123", "sam@sam.no", "12341234");
        User user3 = new User("gsdfg", "qweqwe", "1111", "hunter2",
                "12341234123", "sam@sam.no", "12341234");


        User user2 = DeserialiseUser.deserialiseSpecificId(1);
        System.out.println(user2.getFirstName() + " lives at " + user2.getPostNr());


        Vehicle car = new Car("12341234", "audi", "tt", "petrol",
                "automatic", 2013, 5, 1500);

        Vehicle car2 = DeserialiseVehicle.deserialiseSpecificId(1);
        System.out.println(car2.getRegNo() + " is a " + car2.getManufacturer() + " " + car2.getModel() + " from "
                + car2.getModelYear());

        /*
        RentOutAd roa = new RentOutAd(
                user,
                car,
                BigDecimal.valueOf(200), BigDecimal.valueOf(10), "Sarpsborg"
                );

         */

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
