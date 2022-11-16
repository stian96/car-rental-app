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

    String email = SignUpController.emails;
    String password = SignUpController.pass;







    // action button for create profile

   public void UpdateProfile(ActionEvent event) {

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

        User u = RetrieveUserDB.retrieveFromEmail(email);
       button_UpdateProfile.setText("Added");










    }

    public void exitToMainPage(ActionEvent actionEvent) throws IOException {
         Main m = new Main();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("You must have set up your profile to fully experince the site!");
        alert.setContentText("Press ok to go to Main or cancel to uptade profile");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent()){

        }
        // alert is exited, no button has been pressed.
        else if(result.get() == ButtonType.OK){
            m.changeScene("ToGoCar.fxml");
        }
        //oke button is pressed
        else if(result.get() == ButtonType.CANCEL){



        }






}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_emailAdd.setText(email);
        tf_password.setText(password);

    }
}
