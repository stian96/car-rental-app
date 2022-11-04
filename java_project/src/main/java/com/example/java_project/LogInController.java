package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.RetrieveUserDB;


import java.io.IOException;
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

    public LogInController(){
        super();

    }

    public void userLogIn(ActionEvent event) throws IOException {
        Main m = new Main();
        User u = RetrieveUserDB.retrieveFromEmail(tf_userName.getText());
        String password = tf_password.getText();
        String email = tf_userName.getText();
        if(u.getEmail().equals(email) && u.getPassword().equals(password)){
            wrongLogin.setText("success");
            m.changeScene("ToGoCar.fxml");
        }
        else if (tf_userName.getText().isEmpty() && tf_password.getText().isEmpty()) {
            wrongLogin.setText("Please enter Email and Password.");
        }
        else {
            wrongLogin.setText("Wrong email or password");
        }
    }

    public void userSignUp(ActionEvent event) throws IOException{
        SignUpCheck();
    }
/*
    public void isVerified() throws IOException {
        Main m = new Main();

        HashMap<String, String> loginverification = new HashMap<>();
        loginverification.put("john1@gmail.com", "john1");
        loginverification.put("john2@gmail.com", "john2");
        loginverification.put("john3@gmail.com", "john3");
        for (Map.Entry<String, String> entry : loginverification.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (tf_userName.getText().equals(key) && tf_password.getText().equals(value)) {
                wrongLogin.setText("Success!");
                m.changeScene("ToGoCar.fxml");
            }else if (tf_userName.getText().isEmpty() && tf_password.getText().isEmpty()) {
                wrongLogin.setText("Please enter Email and Password.");
            }
            else {
                wrongLogin.setText("Wrong email or password");
            }


        }

    }*/
    private void SignUpCheck() throws IOException {
        Main m = new Main();
        m.changeScene("SignUp.fxml");

    }
}
