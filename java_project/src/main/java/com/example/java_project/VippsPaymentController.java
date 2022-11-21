package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hiof.groupproject.models.payment_methods.GooglePay;
import no.hiof.groupproject.models.payment_methods.Vipps;
import no.hiof.groupproject.tools.db.InsertPaymentDB;

import java.net.URL;
import java.util.ResourceBundle;

public class VippsPaymentController implements Initializable {
    @FXML
    private TextField tf_EmailAdd;
    @FXML
    private TextField tf_Password;
    @FXML
    private Button button_addPayment;
    @FXML
    private AnchorPane scenePane;

    Stage stage;

    @FXML
    public void btn_AddPayment(ActionEvent event) {
        String tele = tf_EmailAdd.getText();
        String pin= tf_Password.getText();
        Vipps v = new Vipps(tele, pin);
        if(!tele.isEmpty() && !pin.isEmpty()){
            try{

                InsertPaymentDB.insert(v);
                BookingController.pay = v;
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
