package no.hiof.groupproject.gui.Controller.Profile;

import no.hiof.groupproject.gui.Controller.SignUpController;
import no.hiof.groupproject.gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    private Button button_mainMenu;

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
        ) {showErrorlabels();}
        else {
           User user = new User(firstName,lastName,postNumber,password,bankAccNum,email,phoneNum,l);
            button_UpdateProfile.setText("Added");
            //m.changeScene("ToGoCar.fxml");
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
        Main main = new Main();
        try {
            main.changeScene("ToGoCar.fxml");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_emailAdd.setText(email);
        tf_password.setText(password);

        button_mainMenu.setOnAction(this::goToMainMenu);
        addStyle(button_mainMenu);
        addStyleToUpdateButton(button_UpdateProfile);
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
