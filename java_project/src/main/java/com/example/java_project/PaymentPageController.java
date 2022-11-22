package com.example.java_project;

import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.tools.db.InsertPaymentDB;
import no.hiof.groupproject.tools.db.RetrievePaymentDB;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {

    @FXML
    private TextField tf_EmailAdd;
    @FXML
    private TextField tf_Password;
    @FXML
    private Button button_addPayment, backButton;
    @FXML
    private AnchorPane scenePane;

    public static Paypal payment;
    Stage stage;

    @FXML
    public void btn_AddPayment(ActionEvent event) {
        String email = tf_EmailAdd.getText();
        String password = tf_Password.getText();
        Paypal p = new Paypal(email, password);
        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                InsertPaymentDB.insert(p);
                BookingController.pay = p;

                button_addPayment.setText("Added!");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Payment is added!");
                alert.show();

                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }}else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Fill out all fields!");
                alert.show();
            }

        }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_addPayment.setOnAction(this::btn_AddPayment);
    }

    public void buttonStyle() {
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #c9b502;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color:  #f1c232;"));

    }
}
