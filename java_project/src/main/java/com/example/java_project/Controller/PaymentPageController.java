package com.example.java_project.Controller;

import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private Button button_addPayment;

    public static Paypal payment;

    public void btn_AddPayment(ActionEvent event) {
        Main m = new Main();
        String email = tf_EmailAdd.getText();
        String password = tf_Password.getText();
       Payment p = new Paypal(email,password);
    if(!email.isEmpty() && !password.isEmpty()){
        try{
            if(!p.existsInDb()){
                InsertPaymentDB.insert(p);
                payment = (Paypal) p;
                button_addPayment.setText("success");

            }


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
