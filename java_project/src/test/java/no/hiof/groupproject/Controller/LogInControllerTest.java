package no.hiof.groupproject.Controller;

import no.hiof.groupproject.models.loginSignUp_methods.LogInSignUp;
import no.hiof.groupproject.models.loginSignUp_methods.LogInValidUsers;
import no.hiof.groupproject.tools.VerifyLogInSignUp;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LogInControllerTest {

    LogInSignUp logInto;

    @Test
    void checkMemberValiduser() throws IOException {
        VerifyLogInSignUp login = new VerifyLogInSignUp(new LogInValidUsers("jim@hotmail.com", "hunter3"));
        assertFalse(login.isVerified());
    }

}