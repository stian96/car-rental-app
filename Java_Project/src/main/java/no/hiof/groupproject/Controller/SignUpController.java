package no.hiof.groupproject.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    private void SignUpCheck() throws IOException {
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

    private void SignUpCheckGoogle() throws IOException {
        Main m = new Main();

        HashMap<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("John@gmail.com", "cool");
        userInfo.put("John1@gmail.com", "cool1");
        userInfo.put("John2@gmail.com", "cool2");
        userInfo.put("John3@gmail.com", "cool3");
        userInfo.put("John4@gmail.com", "cool4");

        for (Map.Entry<String, String> entry : userInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (tf_userName.getText().toString().equals(key) && tf_password.getText().toString().equals(value)) {
                validEmail.setText("Success!");
                m.changeScene("ToGoCar.fxml");
            }else if (tf_userName.getText().isEmpty() && tf_password.getText().isEmpty()) {
                validEmail.setText("Please enter Email and Password.");
            }
            else {
                validEmail.setText("Invalid username");
            }

        }
    }
    private void SignUpCheckFacebook() throws IOException {
        Main m = new Main();
        HashMap<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("John@gmail.com", "cool");
        userInfo.put("John1@gmail.com", "cool1");
        userInfo.put("John2@gmail.com", "cool2");
        userInfo.put("John3@gmail.com", "cool3");
        userInfo.put("John4@gmail.com", "cool4");

        for (Map.Entry<String, String> entry : userInfo.entrySet()) {
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

    private void LogInCheck() throws IOException{
        Main m = new Main();
        m.changeScene("logIn.fxml");

    }
}
