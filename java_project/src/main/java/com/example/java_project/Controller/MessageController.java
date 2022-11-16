package com.example.java_project.Controller;

import com.example.java_project.Controller.LogInController;
import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.chat.Message;

import java.io.IOException;

public class MessageController {

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
        User user = LogInController.user;
        String message = textArea_send.getText();
        Message messageClass = new Message(user, message);
    }
    public void btnTogoCar(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");
    }

}
