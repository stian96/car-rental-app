package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.loginSignUp_methods.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class VerifyLogInSignUp {

   private LogInSignUp methods_loginSignUp;

    protected HashMap<String, String> loginverification;
    protected HashMap<String, String> signUp;
    protected HashMap<String, String> signUpFacebook;
    protected HashMap<String, String> signUpGoogle;



    public VerifyLogInSignUp(LogInSignUp methods_loginSignUp){
        this.methods_loginSignUp = methods_loginSignUp;

        loginverification = new HashMap<String, String>();
        loginverification.put("john1@gmail.com", "john1");
        loginverification.put("john2@gmail.com", "john2");
        loginverification.put("john3@gmail.com", "john3");

        signUp = new HashMap<String, String>();
        signUp.put("miley1@gmail.com", "miley1");
        signUp.put("miley2@gmail.com", "miley2");
        signUp.put("miley3@gmail.com", "miley3");

        signUpFacebook = new HashMap<String, String>();
        signUpFacebook.put("mike1@gmail.com", "mike1");
        signUpFacebook.put("mike2@gmail.com", "mike2");
        signUpFacebook.put("mike3@gmail.com", "mike3");

        signUpGoogle = new HashMap<String, String>();
        signUpGoogle.put("ken1@gmail.com", "ken1");
        signUpGoogle.put("ken2@gmail.com", "ken2");
        signUpGoogle.put("ken3@gmail.com", "ken3");

    }


    public boolean isVerified() throws IOException {
        if(methods_loginSignUp.getClass() == LogInValidUsers.class){
            LogInValidUsers login = (LogInValidUsers) methods_loginSignUp;
            if(loginverification.containsKey(login.getEmail())){
                return Objects.equals(loginverification.get(login.getEmail()), login.getPassword());
            }
            return false;
        }
        else if(methods_loginSignUp.getClass() == SignUpUser.class){
            SignUpUser login1 = (SignUpUser) methods_loginSignUp;
            if(signUp.containsKey(login1.getEmail())){
                return Objects.equals(signUp.get(login1.getEmail()), login1.getPassword());
            }
            return false;
    }
        else if(methods_loginSignUp.getClass() == SignUpFacebook.class){
            SignUpFacebook login2 = (SignUpFacebook) methods_loginSignUp;
            if(signUpFacebook.containsKey(login2.getEmail())){
                return Objects.equals(signUpFacebook.get(login2.getEmail()), login2.getPassword());
            }
            return false;
        }

        else if(methods_loginSignUp.getClass() == SignUpGoogle.class){
            SignUpGoogle login3 = (SignUpGoogle) methods_loginSignUp;
            if(signUpGoogle.containsKey(login3.getEmail())){
                return Objects.equals(signUpGoogle.get(login3.getEmail()), login3.getPassword());
            }
            return false;
        }
        else {
            return false;
        }

    }

}
