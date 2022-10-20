package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.loginSignUp_methods.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class VerifyLogInSignUpTest {

    LogInSignUp logInto;

    @Test
    void checkMemberValidUser() {
        VerifyLogInSignUp logInto = new VerifyLogInSignUp(new LogInValidUsers("jim@hotmail.com", "hunter3"));
        assertFalse(logInto.isVerified());}

    @Test
    void checkMemberViaSignUp() {
        VerifyLogInSignUp login = new VerifyLogInSignUp(new SignUpFacebook("jim@hotmail.com", "hunter3"));
        assertFalse(login.isVerified());
    }

    @Test
    void checkMemberViaSignUpGoogle() {
        VerifyLogInSignUp login = new VerifyLogInSignUp(new SignUpGoogle("jim@hotmail.com", "hunter3"));
        assertFalse(login.isVerified());
    }

    @Test
    void checkMemberViaSignUpFacebook() {
        VerifyLogInSignUp login = new VerifyLogInSignUp(new SignUpUser("jim@hotmail.com", "hunter3"));
        assertFalse(login.isVerified());
    }


    }
