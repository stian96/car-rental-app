package no.hiof.groupproject.models;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.GetAutoIncrementId;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.tools.db.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Serialise, GetAutoIncrementId, ExistsInDb {

    //auto-incremental id
    //private static int count = 1;
    private int id;

    //integration of a driving license class here would be great - so that VerifyDrivingLicense could be used when
    //a GUI button to rent a car is activated
    private License dLicense;
    private String firstName, lastName, postNr;
    //obviously the password would be encrypted in a final build
    private String password;
    //relevant for receiving payment via card, paypal, vipps etc.
    private String bankAccountNr, email, tlfNr;

    public User(String firstName, String lastName, String postNr, String password,
                String bankAccountNr, String email, String tlfNr, License dLicense) {
        //this.id = count;
        //increments the id by 1
        //count++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postNr = postNr;
        this.password = password;
        this.bankAccountNr = bankAccountNr;
        this.email = email;
        this.tlfNr = tlfNr;
        if (dLicense.verifyLicenseNumber() && dLicense.verifyDateOfIssue()) {
            this.dLicense = dLicense;
        }



        //if no User with the same email is in the database then the User class is serialised and saved
        if (!existsInDb()) {
            serialise();
        } else {
            /*if the User already exists, but only with email and password then the User is updated
                              ********** NOTE ***********
               to avoid DEADLOCKS it's best to update every field at once.
               first create a User with just email and password, then update all the other fields at once by
               instantiating a new User with the same email and password (use .getEmail() etc.), and then supply
               the rest of the member values

               User user6 = new User("testing@updating.fromhere", "breadismyfavouritefood");
               User user7 = new User("ronny", "pickering", "1777", user6.getPassword(),
                                     "12341234123", user6.getEmail(), "12341234", lic3);
             */
            InsertUserDB.updateFromEmailAndPwd(this);
        }
        //the id is automatically incremented when inserted into the database
        //the autoincrement id is fetched and assigned to this instance
        this.setId(getAutoIncrementId());
        UserProfile up = new UserProfile(this);
    }

    public User(String email, String password) {

        this.email = email;
        this.password = password;

        //allows for a User to be serialised in the password with only an email and password
        if (!existsInDb()) {
            InsertUserDB.insertOnlyEmailAndPwd(this);
        }

        this.setId(getAutoIncrementId());

    }



    //serialises the User class and inserts the values into the database
    @Override
    public void serialise() {
            InsertUserDB.insert(this);
    }

    //returns a copy of the current instance of the User class based on this.id
    //NOT CURRENTLY IN USE, but the logic behind the code may be useful later on
    public User deserialise() {
        return RetrieveUserDB.retrieveFromId(this.id);
    }

    //used in conjunction with an autoincremented users_id value in the database
    @Override
    public int getAutoIncrementId() {
        String sql = "SELECT * FROM users WHERE email = \'" + this.email + "\'";

        int i = 0;
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            i = queryResult.getInt("users_id");
            return i;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    //used to check if the email is stored in the database already. Returns true if the email is present
    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM users WHERE email = \'" + this.email + "\'";

        boolean ans = false;
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void deleteUserAndCascade() {
        RemoveUserDB.remove(this.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostNr() {
        return postNr;
    }

    public void setPostNr(String postNr) {
        this.postNr = postNr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankAccountNr() {
        return bankAccountNr;
    }

    public void setBankAccountNr(String bankAccountNr) {
        this.bankAccountNr = bankAccountNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlfNr() {
        return tlfNr;
    }

    public void setTlfNr(String tlfNr) {
        this.tlfNr = tlfNr;
    }

    public License getdLicense() {
        return dLicense;
    }

    public void setdLicense(License dLicense) {
        this.dLicense = dLicense;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", dLicense=" + dLicense +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", postNr='" + postNr + '\'' +
                ", password='" + password + '\'' +
                ", bankAccountNr='" + bankAccountNr + '\'' +
                ", email='" + email + '\'' +
                ", tlfNr='" + tlfNr + '\'' +
                '}';
    }
}
