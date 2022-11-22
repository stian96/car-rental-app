package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerServiceController implements Initializable {
    @FXML
    private TextArea textArea;
    @FXML private Button mainMenuButton;

    public void changeSceneToMainMenu(ActionEvent event){
        Main m = new Main();
        try {
            m.changeScene("ToGoCar.fxml");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setText("Call customer service at +47 945 72 777 \n" +
                "\nEmail us at: customerservice@TOGO.no");
        //mainMenuButton.setOnAction(this::changeSceneToMainMenu);
    }
    public void buttonStyle() {
        mainMenuButton.setOnMouseEntered(e -> mainMenuButton.setStyle("-fx-background-color: #c9b502;"));
        mainMenuButton.setOnMouseExited(e -> mainMenuButton.setStyle("-fx-background-color:  #f1c232;"));}



}
