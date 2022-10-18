package no.hiof.groupproject.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import no.hiof.groupproject.Main;
import no.hiof.groupproject.models.loginSignUp_methods.LogInSignUp;
import no.hiof.groupproject.tools.VerifyLogInSignUp;

import java.io.IOException;
import java.util.Map;

public class SignUpController extends VerifyLogInSignUp {

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


    public SignUpController(LogInSignUp methods_loginSignUp, TextField tf_userName, PasswordField tf_password, Button button_login, Button button_signUp, Button button_signUpGoogle, Button button_signUpFacebook, Label validEmail) {
        super(methods_loginSignUp);
        this.tf_userName = tf_userName;
        this.tf_password = tf_password;
        this.button_login = button_login;
        this.button_signUp = button_signUp;
        this.button_signUpGoogle = button_signUpGoogle;
        this.button_signUpFacebook = button_signUpFacebook;
        this.validEmail = validEmail;
    }

    public void userLogIn(ActionEvent event) throws IOException {
        LogInCheck();
    }

    public void userSignUp(ActionEvent event) throws IOException{
        SignUpCheck();
    }

    public void signUpGoogle(ActionEvent event) throws IOException {
        SignUpCheckGoogle();
    }

    public void signUpFacebook(ActionEvent event) throws IOException{
        SignUpCheckFacebook();
    }

    public void SignUpCheck () throws IOException {
        Main m = new Main();
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
        m.changeScene("logIn.fxml");

    }

    private void SignUpCheckGoogle() throws IOException {
        Main m = new Main();
        m.changeScene("logIn.fxml");

    }

    private void SignUpCheckFacebook() throws IOException {
        Main m = new Main();
        m.changeScene("logIn.fxml");

    }
}
