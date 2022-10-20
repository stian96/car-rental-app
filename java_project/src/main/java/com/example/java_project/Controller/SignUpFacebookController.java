package com.example.java_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import no.hiof.groupproject.Main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpFacebookController {

    @FXML
    private TextField tf_userName;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Button button_signUpFacebook;
    @FXML
    private Button button_login;
    @FXML
    private Label validEmail;


    public void userLogIn(ActionEvent event) throws IOException {
        LoginPage();
    }

    public void signUpFacebook(ActionEvent event) throws IOException{
        SignUpCheckFacebook();
    }

    public void SignUpCheckFacebook() throws IOException {
        Main m = new Main();
        HashMap<String, String> signUpInfo = new HashMap<String, String>();
        signUpInfo.put("John", "cool");
        signUpInfo.put("John1", "cool1");
        signUpInfo.put("John2", "cool2");
        signUpInfo.put("John3", "cool3");
        signUpInfo.put("John4", "cool4");
        for (Map.Entry<String, String> entry : signUpInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (tf_userName.getText().toString().equals(key) && tf_password.getText().toString().equals(value)) {
                validEmail.setText("Success!");
                m.changeScene("ToGoCar.fxml");
            } else if (tf_userName.getText().isEmpty() && tf_password.getText().isEmpty()) {
                validEmail.setText("Please enter Email and Password.");
            } else {
                validEmail.setText("Wrong email or password");
            }


        }

    }

    public void LoginPage() throws IOException {
        Main m = new Main();
        m.changeScene("logIn.fxml");

    }



}
