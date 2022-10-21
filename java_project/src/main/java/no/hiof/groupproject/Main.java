package no.hiof.groupproject;


import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.GenericQueryDB;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {

        GenericQueryDB.query("INSERT INTO people(name,age) VALUES('jesper', 14)");

        User user = new User("sam", "davies", "1111", "hunter2", "1234123412341234",
                "sam@sam.no", "12341234");

    }
}
