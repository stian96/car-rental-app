package no.hiof.groupproject.tools.db.Controller;

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
        VerifyLogInSignUp login = new VerifyLogInSignUp(new LogInValidUsers("john1@gmail.com", "john1"));
        assertTrue(login.isVerified());
    }

}