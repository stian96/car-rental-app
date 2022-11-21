package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hiof.groupproject.models.payment_methods.CreditDebit;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.tools.db.InsertPaymentDB;

import java.net.URL;
import java.util.ResourceBundle;

public class CreditDebitController implements Initializable {

    @FXML
    private TextField tf_EmailAdd;
    @FXML
    private TextField tf_Password,yearTxtfield,monthtxtfield;
    @FXML
    private Button button_addPayment;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label emptyFieldWarning;

    public static Paypal payment;
    Stage stage;

    @FXML
    public void btn_AddPayment(ActionEvent event) {

        String cardNum = tf_EmailAdd.getText();
        String CCV = tf_Password.getText();
        String month = monthtxtfield.getText();
        String year = yearTxtfield.getText();
        CreditDebit c = new CreditDebit(cardNum,CCV,ChangeToInt(month),ChangeToInt(month));
        if(!cardNum.isEmpty() || !CCV.isEmpty()
        ||!month.isEmpty() || !year.isEmpty()){
            try{
                InsertPaymentDB.insert(c);
                BookingController.pay= c;

                button_addPayment.setText("Added!");

                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else{emptyFieldWarning.setText("Fill out all fields");}

    }
    private int ChangeToInt(String value)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_addPayment.setOnAction(this::btn_AddPayment);
    }

}
