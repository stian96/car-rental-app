package no.hiof.groupproject.gui.Controller.Profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hiof.groupproject.gui.Controller.LogInController;
import no.hiof.groupproject.gui.Main;
import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateProfile implements Initializable {
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
    private Button button_mainMenu;

    @FXML
    private Label  error1, error2, error3, error4, error5, error6,
            error7,error8,error9,error10;
    @FXML
    private AnchorPane scenePane;
    Stage stage;
    User user = RetrieveUserDB.retrieveFromEmail(LogInController.user.getEmail());

    public void UpdateProfile(ActionEvent event){

        String firstName = tf_firstName.getText();
        String lastName= tf_lastName.getText();
        String email = tf_emailAdd.getText();
        String password = tf_password.getText();
        String phoneNum = tf_phone.getText();
        String bankAccNum =tf_bankAccnt.getText();
        String postNumber = tf_postNumber.getText();

        String dLicenseNum = tf_drivingLicense.getText();
        LocalDate dateOfIssue = LocalDate.parse(dateTxtField.getText());
        String countryOfIssue = countryTxtField.getText();
        License license = new License(dLicenseNum,dateOfIssue,countryOfIssue);

        if(firstName.equals("") || lastName.equals("") || email.equals("") ||
                password.equals("") || phoneNum.equals("") || bankAccNum.equals("")
                || postNumber.equals("") || dLicenseNum.equals("") || countryOfIssue.equals("")
        ) {showErrorlabels();}
        else{
            try{
                RemoveUserDB.remove(user.getId());
                User user = new User(firstName,lastName,postNumber,password,bankAccNum,email,phoneNum,license);
                button_UpdateProfile.setText("Added");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }

    public void showErrorlabels(){
        Label[] labellist = {error1, error2, error3, error4, error5, error6,
                error7,error8,error9,error10};
        for(Label e : labellist){
            e.setVisible(true);
        }
    }

    public void goToMainMenu(ActionEvent event) {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_mainMenu.setOnAction(this::goToMainMenu);
        button_UpdateProfile.setOnAction(this::UpdateProfile);

        tf_firstName.setText(user.getFirstName());
        tf_lastName.setText(user.getLastName());
        tf_emailAdd.setText(user.getEmail());
        tf_password.setText(user.getPassword());
        tf_phone.setText(user.getTlfNr());
        tf_postNumber.setText(user.getPostNr());
        tf_bankAccnt.setText(user.getBankAccountNr());
        tf_drivingLicense.setText(user.getdLicense().getLicenseNumber());
        dateTxtField.setText(String.valueOf(user.getdLicense().getDateOfIssue()));
        countryTxtField.setText(user.getdLicense().getCountryOfIssue());

    }
    public void addStyle(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color:  #f1c232; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #f1c232; -fx-text-fill: black"));
    }

    public void addStyleToUpdateButton(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color:  #9fc5e8; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color:  #9fc5e8; -fx-text-fill: black"));
    }
}
