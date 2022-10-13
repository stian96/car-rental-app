package no.hiof.groupproject.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogInController {

    @FXML
    private Label wrongLogin;
    @FXML
    private TextField tf_userName;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Button button_login;
    @FXML
    private Button button_signUp;


    public void userLogIn(ActionEvent event) throws IOException{
        LogInCheck();
    }

    public void userSignUp(ActionEvent event) throws IOException{
        SignUpCheck();
    }


    private void LogInCheck() throws IOException{
        Main m = new Main();
        HashMap<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("John", "cool");
        userInfo.put("John1", "cool1");
        userInfo.put("John2", "cool2");
        userInfo.put("John3", "cool3");
        userInfo.put("John4", "cool4");

        for (Map.Entry<String, String> entry : userInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (tf_userName.getText().toString().equals(key) && tf_password.getText().toString().equals(value)) {
                wrongLogin.setText("Success!");
                m.changeScene("ToGoCar.fxml");
            }else if (tf_userName.getText().isEmpty() && tf_password.getText().isEmpty()) {
                wrongLogin.setText("Please enter Email and Password.");
            }
            else {
                wrongLogin.setText("Wrong email or password");
            }

        }
    }

    private void SignUpCheck() throws IOException {
        Main m = new Main();
        m.changeScene("SignUp.fxml");

    }


}
