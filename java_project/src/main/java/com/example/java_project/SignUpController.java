package com.example.java_project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.InsertUserDB;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpController {

    @FXML
    private TextField tf_userName;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Button button_login;
    @FXML
    private Button button_signUp;
    @FXML
    private Button button_signUpGoogle;
    @FXML
    private Button button_signUpFacebook;
    @FXML
    private Label validEmail;


    public SignUpController(){}

    public void userLogIn(ActionEvent event) throws IOException {
        LogInCheck();
    }




    public void userSignUp(ActionEvent event) throws IOException{
        Main m = new Main();
        String email = tf_userName.getText().trim();
        String password = tf_password.getText().trim();
        User user = new User(email,password);
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                if(!user.existsInDb()){
                    user.serialise();
                    m.changeScene("ToGoCar.fxml");

                }else{validEmail.setText("User already exists");
                }

            }catch (IOException e){
                System.out.println(e.getMessage());
            }

        }
        else{ validEmail.setText("Enter email and password");}

    }

    public void signUpGoogle(ActionEvent event) throws IOException {
    }


    public void signUpFacebook(ActionEvent event) throws IOException{
        SignUpCheckFacebook();
    }

    public void isVerified() throws IOException {
        Main m = new Main();
        HashMap<String,String> signUp = new HashMap<>();
        signUp.put("miley1@gmail.com", "miley1");
        signUp.put("miley2@gmail.com", "miley2");
        signUp.put("miley3@gmail.com", "miley3");
        for (Map.Entry<String, String> entry : signUp.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (tf_userName.getText().toString().equals(key) && tf_password.getText().toString().equals(value)) {
                validEmail.setText("Success!");
                m.changeScene("ToGoCar.fxml");
            }else if (tf_userName.getText().isEmpty() && tf_password.getText().isEmpty()) {
                validEmail.setText("Please enter Email and Password.");
            }
            else {
                validEmail.setText("Wrong email or password");
            }


        }

    }

    private void LogInCheck() throws IOException {
        Main m = new Main();
        m.changeScene("LogIn.fxml");

    }

    private void SignUpCheckGoogle() throws IOException {
        Main m = new Main();
        m.changeScene("SignUpGoogle.fxml");

    }

    private void SignUpCheckFacebook() throws IOException {
        Main m = new Main();
        m.changeScene("SignUpFacebook.fxml");

    }

}
