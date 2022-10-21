package no.hiof.groupproject;

import no.hiof.groupproject.models.Car;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.Vehicle;
import no.hiof.groupproject.tools.db.GenericQueryDB;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {

        RentOutAd roa = new RentOutAd(
                new User("Sam", "Davies", "1111", "hunter2",
                        "123412341234", "sam@sam.no", "12345678"),
                new Car(),
                BigDecimal.valueOf(200), BigDecimal.valueOf(10)
        );

        GenericQueryDB.query("INSERT INTO people(name,age) VALUES('jesper', 14)");

    }
}
