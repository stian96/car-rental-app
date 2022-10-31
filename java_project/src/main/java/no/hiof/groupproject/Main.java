package no.hiof.groupproject;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.GenericQueryDB;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Currency;

public class Main {
    public static void main(String[] args) throws IOException {


        //testing instantiation of rentOutAd using dependency injection
        /*
        GenericQueryDB.query("INSERT INTO people(name,age) VALUES('jesper', 14)");
        */


        User user = new User("sam", "davies", "1111", "hunter2",
                "1234123412341234", "sam@sam.no", "12341234");

        /*
        Vehicle car = new Car("12341234", "audi", "tt", "petrol",
                "automatic", 2013, 5, 1500, "a nice one");

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
