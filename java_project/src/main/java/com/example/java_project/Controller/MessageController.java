package com.example.java_project.Controller;

import com.example.java_project.Controller.LogInController;
import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.chat.ChatRoom;
import no.hiof.groupproject.tools.chat.Message;
import no.hiof.groupproject.tools.db.RetrieveUserDB;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {

    @FXML
    private TextArea textArea_send;
    @FXML
    private ScrollPane scroolPane_chat;
    @FXML
    private Button button_send;
    @FXML
    private Button button_toGoCar;
    public void btnSend(ActionEvent event) {
        Main m = new Main();
        User u = LogInController.user;
        String message = textArea_send.getText();
        ChatRoom chat = new ChatRoom();
        Message messageClass = new Message(u, message);
        chat.sendMessage(messageClass);
        button_send.setText("Message\nsent!");
    }
    public void btnTogoCar(ActionEvent event){
        Main main = new Main();
        try {
            main.changeScene("ToGoCar.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_send.setOnAction(this::btnSend);
        button_toGoCar.setOnAction(this::btnTogoCar);

        button_send.setOnMouseEntered(e -> button_send.setStyle("-fx-background-color:  #f1c232; -fx-text-fill: white;"));
        button_toGoCar.setOnMouseExited(e -> button_send.setStyle("-fx-background-color: #f1c232; -fx-text-fill: black"));

    }
}
