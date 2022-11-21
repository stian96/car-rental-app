package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hiof.groupproject.models.payment_methods.GooglePay;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.tools.db.InsertPaymentDB;

import java.net.URL;
import java.util.ResourceBundle;

public class GooglePayController implements Initializable {

    @FXML
    private TextField tf_EmailAdd;
    @FXML
    private TextField tf_Password;
    @FXML
    private Button button_addPayment;
    @FXML
    private AnchorPane scenePane;

    public static GooglePay payment;
    Stage stage;

    @FXML
    public void btn_AddPayment(ActionEvent event) {
        Main m = new Main();
        String email = tf_EmailAdd.getText();
        String password = tf_Password.getText();
        GooglePay p = new GooglePay(email,password);
        if(!email.isEmpty() && !password.isEmpty()){
            try{

                InsertPaymentDB.insert(p);
                BookingController.pay = p;
                button_addPayment.setText("Added!");
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();





            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_addPayment.setOnAction(this::btn_AddPayment);
    }

}
