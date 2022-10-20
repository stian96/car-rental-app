package no.hiof.groupproject.models.loginSignUp_methods;

public abstract class Registration{

    String email;
    String password;

    public Registration(String email, String password) {

        if (email.indexOf('@') != -1 && password.length() > 0)
        this.email = email;
        this.password = password;
    }

    public Registration(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
