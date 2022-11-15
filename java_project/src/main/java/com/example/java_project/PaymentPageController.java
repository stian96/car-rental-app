package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.tools.db.InsertPaymentDB;
import no.hiof.groupproject.tools.db.RetrievePaymentDB;

import java.io.IOException;

public class PaymentPageController {

    @FXML
    private TextField tf_EmailAdd;
    @FXML
    private TextField tf_Password;
    @FXML
    private Button button_addPayment;

    public void btn_AddPayment(ActionEvent event)throws IOException {
        Main m = new Main();
        String email = tf_EmailAdd.getText();
        String password = tf_Password.getText();
       Paypal p = new Paypal(email,password);
    if(!email.isEmpty() && !password.isEmpty()){
        try{
            if(!p.existsInDb()){
                Payment pay = InsertPaymentDB.insert(p);

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}}
