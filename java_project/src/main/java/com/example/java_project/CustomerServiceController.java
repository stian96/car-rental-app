package com.example.java_project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerServiceController implements Initializable {
    @FXML
    private TextArea textArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setText("Call customer service at +47 945 72 777 \n" +
                " Email us at: customerservice@TOGO.no");
    }
}
