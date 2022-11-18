package no.hiof.groupproject.tools;


import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.models.advertisements.Advertisement;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveUserProfileDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;
import no.hiof.groupproject.tools.db.RetrieveVehiclesDB;

import java.util.ArrayList;

public class GDPRInformation {

    UserProfile up;
    ArrayList<String> arrayOfPrintOut;

    public GDPRInformation(int userId) {
        up = RetrieveUserProfileDB.retrieve(userId);
        arrayOfPrintOut = new ArrayList<>();
        retrieveArrayOfPrintOut();
    }

    public void retrieveArrayOfPrintOut() {
        User user = up.getUser();
        String fullName = user.getFirstName() + " " + user.getLastName();
        String email = user.getEmail();
        String tlfNr = user.getTlfNr();
        String bankAccountNr = user.getBankAccountNr();
        String postNr = user.getPostNr();
        String password = user.getPassword();

        String licNr = user.getdLicense().getLicenseNumber();
        String licCtry = user.getdLicense().getCountryOfIssue();
        String licDate = user.getdLicense().getDateOfIssue().toString();

        ArrayList<Advertisement> advertisements = up.getAdvertisements();

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (int i : RetrieveVehiclesDB.retrieveAllVehiclesIdLinkedToUser(up.getUser().getId())) {
            vehicles.add(RetrieveVehicleDB.retrieveFromId(i));
        }

        int nrOfAds = advertisements.size();
        int nrOfVehicles = vehicles.size();

        for (Advertisement ad : advertisements) {
            if (ad.getClass() == RentOutAd.class) {
                ad = ((RentOutAd)ad);
            }
        }
        arrayOfPrintOut.add("\n*** Personal Information ***\n");
        arrayOfPrintOut.add("*        Full name: " + fullName);
        arrayOfPrintOut.add("*            Email: " + email);
        arrayOfPrintOut.add("*         Password: " + password);
        arrayOfPrintOut.add("*    Telephone No.: " + tlfNr);
        arrayOfPrintOut.add("* Bank Account No.: " + bankAccountNr);
        arrayOfPrintOut.add("*         Location: " + postNr + ", Norway");

        arrayOfPrintOut.add("\n*** License ***\n");
        arrayOfPrintOut.add("*  Driving License: " + licNr);
        arrayOfPrintOut.add("* Country of Issue: " + licCtry);
        arrayOfPrintOut.add("*    Date of Issue: " + licDate);

        arrayOfPrintOut.add("\n*** Registered Vehicles ***\n");
        arrayOfPrintOut.add("Number of Registered Vehicles: " + nrOfVehicles + "\n");
        for (Vehicle v : vehicles) {
            String vehicleInfo = v.getModelYear() + " " + v.getManufacturer() + " " + v.getModel();
            String vehicleInfoTwo = v.getRegNo();
            String vehicleInfoThree = v.getEngineType();
            String vehicleInfoFour = v.getGearType();
            arrayOfPrintOut.add("*          Vehicle: " + vehicleInfo);
            arrayOfPrintOut.add("*          Reg No.: " + vehicleInfoTwo);
            arrayOfPrintOut.add("*           Engine: " + vehicleInfoThree);
            arrayOfPrintOut.add("*     Transmission: " + vehicleInfoFour + "\n");
        }

        arrayOfPrintOut.add("*** Registered Advertisements ***\n");
        arrayOfPrintOut.add("Number of Advertisements: " + nrOfAds + "\n");
        for (Advertisement ad : advertisements) {
            if (ad.getClass() == RentOutAd.class) {
                String town = ((RentOutAd) ad).getTown() + ", ";
                String postNumber =  ((RentOutAd) ad).getPostnr() + ", ";
                String fylke = ((RentOutAd) ad).getFylke() + ", ";
                String country = ((RentOutAd) ad).getLand();
                Vehicle v = ((RentOutAd) ad).getVehicle();
                String vehicleInfo = v.getModelYear() + " " + v.getManufacturer() + " " + v.getModel();
                String vehicleReg = v.getRegNo();
                int NrOfBookings = ((RentOutAd) ad).getConfirmedBookings().size();
                String priceDaily = ((RentOutAd) ad).getDailyCharge().toString();
                arrayOfPrintOut.add("*          Vehicle: " + vehicleReg + ", ");
                arrayOfPrintOut.add("*                   " + vehicleInfo);
                arrayOfPrintOut.add("*         Location: " + town );
                arrayOfPrintOut.add("*                   " + postNumber);
                arrayOfPrintOut.add("*                   " + fylke);
                arrayOfPrintOut.add("*                   " + country);
                arrayOfPrintOut.add("*     No. Bookings: " + NrOfBookings);
                arrayOfPrintOut.add("*     Daily Charge: " + priceDaily + " NOK\n");
            }
        }

    }

    public void print() {
        for (String s : arrayOfPrintOut) {
            System.out.println(s);
        }
    }
    public ArrayList<String> getArrayOfPrintOut() {
        return arrayOfPrintOut;
    }
}
