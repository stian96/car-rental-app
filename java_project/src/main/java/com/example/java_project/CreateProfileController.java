package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.RetrieveUserDB;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateProfileController implements Initializable {
    @FXML
    private TextField tf_firstName;
    @FXML
    private TextField tf_lastName;
    @FXML
    private TextField tf_emailAdd;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_bankAccnt;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_postNumber;

    @FXML
    private Button button_UpdateProfile,exitToMainButton;
    @FXML
    private Label UpdatePrompt;
    @FXML
    private TextField tf_drivingLicense, countryTxtField, dateTxtField;

    @FXML
    private Label  error1, error2, error3, error4, error5, error6,
    error7,error8,error9,error10;

    String email = SignUpController.emails;
    String password = SignUpController.pass;







    // action button for create profile

   public void UpdateProfile(ActionEvent event) throws IOException {

        Main m = new Main();
        String firstName = tf_firstName.getText();
        String lastName= tf_lastName.getText();
        String email = this.email;
        String password = this.password;
        String phoneNum = tf_phone.getText();
        String bankAccNum =tf_bankAccnt.getText();
        String postNumber = tf_postNumber.getText();

        String dLicenseNum = tf_drivingLicense.getText();
        LocalDate dateOfIssue = LocalDate.parse(dateTxtField.getText());
        String countryOfIssue = countryTxtField.getText();

        License l = new License(dLicenseNum,dateOfIssue,countryOfIssue);
        if(firstName.equals("") || lastName.equals("") || email.equals("") ||
                password.equals("") || phoneNum.equals("") || bankAccNum.equals("")
                || postNumber.equals("") || dLicenseNum.equals("") || countryOfIssue.equals("")
        ) showErrorlabels();
        else {
            User u = RetrieveUserDB.retrieveFromEmail(email);
            button_UpdateProfile.setText("Added");
            m.changeScene("ToGoCar.fxml");
        }

    }

    public void showErrorlabels(){
       Label[] labellist = {error1, error2, error3, error4, error5, error6,
               error7,error8,error9,error10};
       for(Label e : labellist){
           e.setVisible(true);
       }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_emailAdd.setText(email);
        tf_password.setText(password);

    }
}
