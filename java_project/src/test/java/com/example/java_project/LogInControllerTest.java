package com.example.java_project;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

class LogInControllerTest {
    /*
    LogInController logInController = mock(LogInController.class);
    LogInController test = new LogInController();
    RetrieveUserDB userDB = mock(RetrieveUserDB.class);

    User user = RetrieveUserDB.retrieveFromEmail(test.getEmail());



    @Test
    void verify_email_is_returned_from_database_is_same_as_user(){

      //String email =user.getEmail();
      assertEquals(user.getEmail(),test.getEmail());
    }
    @Test
    void password_is_same(){
        assertEquals(user.getPassword(),test.getPassword());
    }

    @Test
    void log_in_valid_user(){

        boolean t =logInController.logInValidUser(test.getEmail(),test.getPassword());
        Assertions.assertFalse(t,test.getEmail());

    }



    @Test
    void log_in_valid_users(){

       // boolean t =logInController.logInValidUser(user.getEmail(),test.getPassword());
        Assertions.assertEquals(test.getPassword(),user.getPassword());

    }
    @Test
    void test_this_stuff(){
        logInController.userLogIn();
    }




     */


}