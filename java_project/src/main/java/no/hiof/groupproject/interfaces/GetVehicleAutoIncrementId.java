package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.tools.db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//used to retrieve an int value of the id/primary key (users_id) based on a specific email
public interface GetVehicleAutoIncrementId {

    static int getSpecificAutoIncrementId(String regNo) {
        String sql = "SELECT * FROM vehicles WHERE regNo = " + regNo;

        int i = 0;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            i = queryResult.getInt("vehicles_id");
            return i;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
}
