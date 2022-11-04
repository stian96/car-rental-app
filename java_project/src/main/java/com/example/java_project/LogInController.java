package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.loginSignUp_methods.LogInValidUsers;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LogInController {

    @FXML
    protected Label wrongLogin;
    @FXML
    protected TextField tf_userName;
    @FXML
    protected PasswordField tf_password;
    @FXML
    protected Button button_logIn;
    @FXML
    protected Button button_signUp;

    public void userLogIn(ActionEvent event) throws IOException {
        Main m = new Main();
        User u = RetrieveUserDB.retrieveFromEmail(tf_userName.getText().trim());
        String password = tf_password.getText().trim();
        String email = tf_userName.getText().trim();
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                if(!u.existsInDb()){
                    SignUpCheck();
                }
                else if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                    m.changeScene("ToGoCar.fxml");
                }
                else {wrongLogin.setText("Wrong email or password");}
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        else {wrongLogin.setText("Enter email and Password");
        }
    }

    public void userSignUp(ActionEvent event) throws IOException{
        SignUpCheck();
    }

    private void SignUpCheck() throws IOException {
        Main m = new Main();
        m.changeScene("SignUp.fxml");

    }

}
