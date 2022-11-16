package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.chat.ChatRoom;
import no.hiof.groupproject.tools.chat.Message;
import no.hiof.groupproject.tools.db.RetrieveUserDB;

import java.io.IOException;
import java.util.ArrayList;

public class MessageController {

    @FXML
    private TextArea textArea_send;
    @FXML
    private ScrollPane scroolPane_chat;
    @FXML
    private Button button_send;
    @FXML
    private Button button_toGoCar;

    public void btnSend(ActionEvent event) throws IOException {
        Main m = new Main();
        ChatRoom chat = new ChatRoom();
        String message = textArea_send.getText();
        //
        //      chat.sendMessage(new Message(user, "Hello this is a new message!"));


        for (String msg : chat.getMessageLog()) {
            System.out.println(msg);
        }


    }

    public void btnTogoCar(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");
    }

}
