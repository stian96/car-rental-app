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
import java.util.Locale;
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

    @Test
    void assertsFilterToManufacturer() {

        ArrayList<Vehicle> listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                null, null, "aston martin",
                null, null, null,
                null, null, null, null);

        boolean result = false;
        for (Vehicle vehicle : listOfAds) {

            if (!Objects.equals(vehicle.getManufacturer().toLowerCase(), "aston martin")) {
                result = true;
                break;
            }
        }

        assertFalse(result);

        if (result) {
            result = false;
        }

        listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                null, null, "audi",
                null, null, null,
                null, null, null, null);

        for (Vehicle vehicle : listOfAds) {

            if (!Objects.equals(vehicle.getManufacturer().toLowerCase(), "audi")) {
                result = true;
                break;
            }
        }

        assertFalse(result);

    }

    @Test
    void assertsFilterToDailyCharge() {

        ArrayList<Advertisement> listOfAds = FilterAdvertisement.filterToArrayListAdvertisement(
                null, null, null,
                null, 400, null,
                null, null, null, null);

        boolean result = false;
        for (Advertisement advertisement : listOfAds) {


            //checks to see if the dailyCharge is more than 400. if so, then result = true
            if (((RentOutAd) advertisement).getDailyCharge().compareTo(BigDecimal.valueOf(400)) > 0) {
                result = true;
                break;
            }
        }

        assertFalse(result);

    }

    @Test
    void assertsFilterToChargePerTwentyKm() {

        ArrayList<Advertisement> listOfAds = FilterAdvertisement.filterToArrayListAdvertisement(
                null, null, null,
                null, null, 15,
                null, null, null, null);

        boolean result = false;
        for (Advertisement advertisement : listOfAds) {


            //checks to see if the dailyCharge is more than 400. if so, then result = true
            if (((RentOutAd) advertisement).getChargePerTwentyKm().compareTo(BigDecimal.valueOf(15)) > 0) {
                result = true;
                break;
            }
        }

        assertFalse(result);

    }

    @Test
    void assertsFilterToModelYear() {

        ArrayList<Vehicle> listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                null, null, null,
                null, null, null,
                1990, null, null, null);

        boolean result = false;
        for (Vehicle vehicle : listOfAds) {


            //checks to see if the dailyCharge is more than 400. if so, then result = true
            if (vehicle.getModelYear() < 1990 ) {
                result = true;
                break;
            }
        }

        assertFalse(result);

    }

    @Test
    void assertsFilterToSeatingCapacity() {

        ArrayList<Vehicle> listOfAds = FilterAdvertisement.filterToArrayListVehicle(
                null, null, null,
                null, null, null,
                null, 3, null, null);

        boolean result = false;
        for (Vehicle vehicle : listOfAds) {


            //checks to see if the dailyCharge is more than 400. if so, then result = true
            if (((Car) vehicle).getSeatingCapacity() < 3 ) {
                result = true;
                break;
            }
        }

        assertFalse(result);

    }

    @Test
    void assertsFilterToAverageRating() {

        ArrayList<Advertisement> listOfAds = FilterAdvertisement.filterToArrayListAdvertisement(
                null, null, null,
                null, null, null,
                null, null, 4, null);

        boolean result = false;
        for (Advertisement advertisement : listOfAds) {

            UserProfile up = RetrieveUserProfileDB.retrieve(advertisement.getUser().getId());

            //checks to see if the dailyCharge is more than 400. if so, then result = true
            if (up.getAverageRating() < 4) {
                result = true;
                break;
            }
        }

        assertFalse(result);
    }

    @Test
    void assertsFilterLimitCanBeSpecified() {

        ArrayList<Integer> listOfAds = FilterAdvertisement.filterToArrayListAdvertisementId(
                null, null, null,
                null, null, null,
                null, null, null, 2);

        int counter = 0;
        for (Integer integer : listOfAds) {
            counter++;
        }

        assertEquals(2, counter);
    }

    @Test
    void assertsFilterRatingSortsByHighestAutomatically() {

        ArrayList<Advertisement> listOfAds = FilterAdvertisement.filterToArrayListAdvertisement(
                null, null, null,
                null, null, null,
                null, null, null, 2);

        double averageRatingOne = 0;
        double averageRatingTwo = 0;
        for (int i = 0; i < 2; i++) {

            int userId = listOfAds.get(i).getUser().getId();
            UserProfile up = RetrieveUserProfileDB.retrieve(userId);

            if (i == 0) {
                averageRatingOne = up.getAverageRating();
            } else {
                averageRatingTwo = up.getAverageRating();
            }
        }

        assertTrue(averageRatingOne > averageRatingTwo);

    }

}