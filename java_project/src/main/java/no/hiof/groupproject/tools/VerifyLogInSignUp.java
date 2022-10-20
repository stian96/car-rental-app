package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.loginSignUp_methods.Registration;
import no.hiof.groupproject.models.loginSignUp_methods.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class VerifyLogInSignUp {

    protected Registration methods_loginSignUp;

    public HashMap<String, String> loginVerification;
    public HashMap<String, String> signUp;
    public HashMap<String, String> signUpFacebook;
    public HashMap<String, String> signUpGoogle;


    public VerifyLogInSignUp(Registration methods_loginSignUp){
        this.methods_loginSignUp = methods_loginSignUp;

        loginVerification = new HashMap<>();
        loginVerification.put("john1@gmail.com", "john1");
        loginVerification.put("john2@gmail.com", "john2");
        loginVerification.put("john3@gmail.com", "john3");

        signUp = new HashMap<>();
        signUp.put("miley1@gmail.com", "miley1");
        signUp.put("miley2@gmail.com", "miley2");
        signUp.put("miley3@gmail.com", "miley3");

        signUpFacebook = new HashMap<>();
        signUpFacebook.put("mike1@gmail.com", "mike1");
        signUpFacebook.put("mike2@gmail.com", "mike2");
        signUpFacebook.put("mike3@gmail.com", "mike3");

        signUpGoogle = new HashMap<>();
        signUpGoogle.put("ken1@gmail.com", "ken1");
        signUpGoogle.put("ken2@gmail.com", "ken2");
        signUpGoogle.put("ken3@gmail.com", "ken3");


    }


    public boolean isVerified() {
        if(methods_loginSignUp.getClass() == LogInValidUsers.class){
            LogInValidUsers login = (LogInValidUsers) methods_loginSignUp;
            if(loginVerification.containsKey(login.getEmail())){
                return Objects.equals(loginVerification.get(login.getEmail()), login.getPassword());
            }
            return false;
        }
        else if(methods_loginSignUp.getClass() == SignUpUser.class){
            SignUpUser login = (SignUpUser) methods_loginSignUp;
            if(signUp.containsKey(login.getEmail())){
                return Objects.equals(signUp.get(login.getEmail()), login.getPassword());
            }
            return false;
        }
        else if(methods_loginSignUp.getClass() == SignUpFacebook.class){
            SignUpFacebook login = (SignUpFacebook) methods_loginSignUp;
            if(signUpFacebook.containsKey(login.getEmail())){
                return Objects.equals(signUpFacebook.get(login.getEmail()), login.getPassword());
            }
            return false;
        }

        else if(methods_loginSignUp.getClass() == SignUpGoogle.class){
            SignUpGoogle login = (SignUpGoogle) methods_loginSignUp;
            if(signUpGoogle.containsKey(login.getEmail())){
                return Objects.equals(signUpGoogle.get(login.getEmail()), login.getPassword());
            }
            return false;
        }
        else {
            return false;
        }

    }

}