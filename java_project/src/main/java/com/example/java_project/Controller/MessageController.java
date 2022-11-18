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
    @FXML
    private TextArea messageArea;

    // Global chat room instance.
    ChatRoom chat = new ChatRoom();

    public void btnSend(ActionEvent event) {
        User user = LogInController.user;
        chat.sendMessage(new Message(user, textArea_send.getText()));
        textArea_send.setText(null);
        showMessagesInScrollPane();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_toGoCar.setOnAction(this::btnTogoCar);
        button_send.setOnAction(this::btnSend);
        addStyle(button_toGoCar);
        addStyle(button_send);

        scroolPane_chat.setContent(messageArea);
        showMessagesInScrollPane();
    }

    public void showMessagesInScrollPane() {
        StringBuilder buildLog = new StringBuilder();
        for (String messages : chat.getMessageLog()) {
            buildLog.append(messages).append("\n");
        }
        messageArea.setText(buildLog.toString());
    }

    public void btnTogoCar(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("ToGoCar.fxml");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    public void addStyle(Button button) {
        if (button.equals(button_send)) {
            button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #9fc5e8; -fx-text-fill: white"));
            button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #9fc5e8; -fx-text-fill: black"));
        }
        else {
            button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #f1c232; -fx-text-fill: white"));
            button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #f1c232; -fx-text-fill: black"));
        }

    }
}
