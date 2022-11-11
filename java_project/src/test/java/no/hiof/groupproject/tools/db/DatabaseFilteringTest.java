package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.*;
import no.hiof.groupproject.models.payment_methods.CreditDebit;
import no.hiof.groupproject.models.payment_methods.GooglePay;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.payment_methods.Vipps;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseFilteringTest {

   @BeforeEach
    void initialiseDatabasePath() {
       ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
   }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }

    @Test
    void assertsFilterToArrayListAdvertisementFunctions() {

        ArrayList<Advertisement> listOfAds = FilterAdvertisement.filterToArrayListAdvertisement(
                "automatic", null, null,
                null, null, null,
                null, null, null, null);

        boolean result = false;
        for (Advertisement advertisement : listOfAds) {
            Vehicle vehicle = ((RentOutAd) advertisement).getVehicle();

            if (!Objects.equals(vehicle.getGearType(), "automatic")) {
                result = true;
                break;
            }
        }

        assertFalse(result);
    }

    @Test
    void assertsFilterToArrayListIntegerAdvertisementIdFunctions() {

        ArrayList<Integer> listOfAds = FilterAdvertisement.filterToArrayListAdvertisementId(
                "automatic", null, null,
                null, null, null,
                null, null, null, null);

        boolean result = false;
        for (Integer advertisementId : listOfAds) {
            Advertisement advertisement = RetrieveAdvertisementDB.retrieveFromId(advertisementId);
            Vehicle vehicle = ((RentOutAd) advertisement).getVehicle();

            if (!Objects.equals(vehicle.getGearType(), "automatic")) {
                result = true;
                break;
            }
        }

        assertFalse(result);
    }

    @Test
    void assertsFilterToArrayListVehicleFunctions() {

        ArrayList<Vehicle> listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                "automatic", null, null,
                null, null, null,
                null, null, null, null);

        boolean result = false;
        for (Vehicle vehicle : listOfAds) {

            if (!Objects.equals(vehicle.getGearType(), "automatic")) {
                result = true;
                break;
            }
        }

        assertFalse(result);
    }

    @Test
    void assertsFilterToArrayListVehicleIdFunctions() {

        ArrayList<Integer> listOfAds = FilterAdvertisement.filterToArrayListVehicleId(
                "automatic", null, null,
                null, null, null,
                null, null, null, null);

        boolean result = false;
        for (Integer vehicleId : listOfAds) {
            Vehicle vehicle = RetrieveVehicleDB.retrieveFromId(vehicleId);

            if (!Objects.equals(vehicle.getGearType(), "automatic")) {
                result = true;
                break;
            }
        }

        assertFalse(result);
    }

    @Test
    void assertsFilterToGearType() {

        ArrayList<Vehicle> listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                "automatic", null, null,
                null, null, null,
                null, null, null, null);

        boolean result = false;
        for (Vehicle vehicle : listOfAds) {

            if (!Objects.equals(vehicle.getGearType(), "automatic")) {
                result = true;
                break;
            }
        }

        assertFalse(result);

        if (result) {
            result = false;
        }

        listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                "manual", null, null,
                null, null, null,
                null, null, null, null);

        for (Vehicle vehicle : listOfAds) {

            if (!Objects.equals(vehicle.getGearType(), "manual")) {
                result = true;
                break;
            }
        }

        assertFalse(result);
    }

    @Test
    void assertsFilterToEngineType() {

        ArrayList<Vehicle> listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                null, "petrol", null,
                null, null, null,
                null, null, null, null);

        boolean result = false;
        for (Vehicle vehicle : listOfAds) {

            if (!Objects.equals(vehicle.getEngineType(), "petrol")) {
                result = true;
                break;
            }
        }

        assertFalse(result);

        if (result) {
            result = false;
        }

        listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                null, "diesel", null,
                null, null, null,
                null, null, null, null);

        for (Vehicle vehicle : listOfAds) {

            if (!Objects.equals(vehicle.getEngineType(), "diesel")) {
                result = true;
                break;
            }
        }

        assertFalse(result);

        if (result) {
            result = false;
        }

        listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                null, "electric", null,
                null, null, null,
                null, null, null, null);

        for (Vehicle vehicle : listOfAds) {

            if (!Objects.equals(vehicle.getEngineType(), "electric")) {
                result = true;
                break;
            }
        }

        assertFalse(result);
    }

    @Test
    void assertsFilterToLocation() {

        ArrayList<Advertisement> listOfAds = FilterAdvertisement.filterToArrayListAdvertisement(
                null, null, null,
                "sarpsborg", null, null,
                null, null, null, null);

        boolean result = false;
        for (Advertisement advertisement : listOfAds) {

            if (!((RentOutAd) advertisement).getTown().equalsIgnoreCase("sarpsborg")) {
                result = true;
                break;
            }
        }

        assertFalse(result);

        if (result) {
            result = false;
        }

        listOfAds = FilterAdvertisement.filterToArrayListAdvertisement(
                null, null, null,
                "halden", null, null,
                null, null, null, null);


        for (Advertisement advertisement : listOfAds) {

            if (!((RentOutAd) advertisement).getTown().equalsIgnoreCase("halden")) {
                result = true;
                break;
            }
        }

        assertFalse(result);
    }


}