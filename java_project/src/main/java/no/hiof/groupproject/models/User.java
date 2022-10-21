package no.hiof.groupproject.models;

public class User {

    //auto-incremental id
    private static int count = 1;
    private int id;

    //integration of a driving license class here would be great - so that VerifyDrivingLicense could be used when
    //a GUI button to rent a car is activated
    //private DrivingLicence dLicense:


    private String firstName, lastName, postNr;
    //obviously the password would be encrypted in a final build
    private String password;
    //relevant for receiving payment via card, paypal, vipps etc.
    private String bankAccountNr, email, tlfNr;

    public User(String firstName, String lastName, String postNr, String password,
                String bankAccountNr, String email, String tlfNr) {
        this.id = count;
        //increments the id by 1
        count++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postNr = postNr;
        this.password = password;
        this.bankAccountNr = bankAccountNr;
        this.email = email;
        this.tlfNr = tlfNr;
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
}
