package com.example.java_project.Controller;

import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpGoogleController {


    @FXML
    private TextField tf_userName;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Button button_google;
    @FXML
    private Button button_login;
    @FXML
    private Label validEmail;

    public SignUpGoogleController(TextField tf_userName, PasswordField tf_password, Button button_signUpGoogle, Button button_login, Label validEmail) {
        this.tf_userName = tf_userName;
        this.tf_password = tf_password;
        this.button_google = button_signUpGoogle;
        this.button_login = button_login;
        this.validEmail = validEmail;
    }

    public SignUpGoogleController() {
    }

    public void userLogIn(ActionEvent event) throws IOException {
        GotoLoginPage();
    }

    public void signUpGoogle(ActionEvent event) throws IOException {
        SignUpCheckGoogle();
    }

    public void SignUpCheckGoogle() throws IOException {
        Main m = new Main();
        HashMap<String, String> signUpGoogle = new HashMap<String, String>();
        signUpGoogle.put("John", "cool");
        signUpGoogle.put("John1", "cool1");
        signUpGoogle.put("John2", "cool2");
        signUpGoogle.put("John3", "cool3");
        signUpGoogle.put("John4", "cool4");
        for (Map.Entry<String, String> entry : signUpGoogle.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (tf_userName.getText().toString().equals(key) && tf_password.getText().toString().equals(value)) {
                validEmail.setText("Success!");
                m.changeScene("ToGoCar.fxml");
            }else if (tf_userName.getText().isEmpty() && tf_password.getText().isEmpty()) {
                validEmail.setText("Please enter an email and password.");
            }
            else {
                validEmail.setText("The email or password is incorrect");
            }

        }

    }

    public void GotoLoginPage() throws IOException {
        Main m = new Main();
        m.changeScene("LogIn.fxml");

    }

}