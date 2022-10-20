package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.loginSignUp_methods.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;

class VerifyLogInSignUpTest {

    LogInSignUp logInto;

    @Test
    void checkMemberValiduser() throws IOException {
        VerifyLogInSignUp login = new VerifyLogInSignUp(new LogInValidUsers("jim@hotmail.com", "hunter3"));
        assertFalse(login.isVerified());
    }

    @Test
    void checkMemberviaSignUp() throws IOException {
        VerifyLogInSignUp login = new VerifyLogInSignUp(new SignUpFacebook("jim@hotmail.com", "hunter3"));
        assertFalse(login.isVerified());
    }

    @Test
    void checkMemberViaSignUpGoogle() throws IOException {
        VerifyLogInSignUp login = new VerifyLogInSignUp(new SignUpGoogle("jim@hotmail.com", "hunter3"));
        assertFalse(login.isVerified());
    }

    @Test
    void checkMemberViaSignUpFacebook() throws IOException {
        VerifyLogInSignUp login = new VerifyLogInSignUp(new SignUpUser("jim@hotmail.com", "hunter3"));
        assertFalse(login.isVerified());
    }




}