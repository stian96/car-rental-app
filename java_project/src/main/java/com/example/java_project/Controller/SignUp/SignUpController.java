package com.example.java_project.Controller.SignUp;


import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.InsertUserDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;

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

    public static String emails;
    public static String pass;


    public void userLogIn(ActionEvent event) throws IOException {
        LogInCheck();
    }




    public void userSignUp(ActionEvent event) throws IOException{
        Main m = new Main();
        String email = tf_userName.getText().trim();
        String password = tf_password.getText().trim();
        User u = RetrieveUserDB.retrieveFromEmail(email);
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                if(!u.existsInDb()){
                    User user = new User(email,password);
                    emails = email;
                    pass = password;
                    LogInCheck();
                }else{validEmail.setText("This email already exists!");}
            }
            catch (IOException e){
                System.out.println(e.getMessage());}}
        else{ validEmail.setText("Enter email and password");}
    }


    public void signUpGoogle(ActionEvent event) throws IOException {
        SignUpCheckGoogle();
    }

    public void signUpFacebook(ActionEvent event) throws IOException{
        SignUpCheckFacebook();
    }


    private void LogInCheck() throws IOException {
        Main m = new Main();
        m.changeScene("UpdateProfile.fxml");

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
