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

public class LogInController extends VerifyLogInSignUp {

    @FXML
    protected Label wrongLogin;
    @FXML
    protected TextField tf_userName;
    @FXML
    protected PasswordField tf_password;
    @FXML
    protected Button button_login;
    @FXML
    protected Button button_signUp;

    public LogInController(LogInSignUp methods_loginSignUp, Label wrongLogin, TextField tf_userName, PasswordField tf_password, Button button_login, Button button_signUp) {
        super(methods_loginSignUp);
        this.wrongLogin = wrongLogin;
        this.tf_userName = tf_userName;
        this.tf_password = tf_password;
        this.button_login = button_login;
        this.button_signUp = button_signUp;
    }

    public void userLogIn(ActionEvent event) throws IOException {
        LogInCheck();
    }

    public void userSignUp(ActionEvent event) throws IOException{
        SignUpCheck();
    }

    public void LogInCheck () throws IOException {
        Main m = new Main();
        for (Map.Entry<String, String> entry : loginverification.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (tf_userName.getText().toString().equals(key) && tf_password.getText().toString().equals(value)) {
                wrongLogin.setText("Success!");
                m.changeScene("ToGoCar.fxml");
            } else if (tf_userName.getText().isEmpty() && tf_password.getText().isEmpty()) {
                wrongLogin.setText("Please enter Email and Password.");
            } else {
                wrongLogin.setText("Wrong email or password");
            }

        }

    }
    private void SignUpCheck() throws IOException {
        Main m = new Main();
        m.changeScene("SignUp.fxml");

    }
}
