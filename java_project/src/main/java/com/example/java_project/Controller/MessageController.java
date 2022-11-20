package com.example.java_project.Controller;

import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.chat.ChatRoom;
import no.hiof.groupproject.tools.chat.Message;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import no.hiof.groupproject.tools.db.RetrieveUserProfileDB;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML
    private ChoiceBox<String> receiverChoice;
    @FXML
    private Label receiverLabel;


    // Global chat room instance.
    ChatRoom chat = new ChatRoom();

    ArrayList<User> receiverList = new ArrayList<>();
    User user = LogInController.user;

    public void btnSend(ActionEvent event) {

        for (User receiver : receiverList) {
            if (receiver.getFirstName().equals(receiverChoice.getValue())) {
                chat.sendMessage(new Message(user, receiver, textArea_send.getText()));
            }
        }
        textArea_send.setText(null);
        showMessagesInScrollPane();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        receiverChoice.setOnAction(this::getSelectedChoice);
        button_toGoCar.setOnAction(this::btnTogoCar);
        button_send.setOnAction(this::btnSend);
        addStyle(button_toGoCar);
        addStyle(button_send);

        scroolPane_chat.setContent(messageArea);
        showMessagesInScrollPane();

        getReceiversFromDataBase();
        addReceiversToChoiceBox();

    }

    public void addReceiversToChoiceBox() {
        receiverChoice.getItems().add("None");
        for (User firstname : receiverList)
            receiverChoice.getItems().add(firstname.getFirstName());
    }

    public void getReceiversFromDataBase() {
        receiverList.add(RetrieveUserDB.retrieveFromId(7));
        receiverList.add(RetrieveUserDB.retrieveFromId(8));
        receiverList.add(RetrieveUserDB.retrieveFromId(12));
        receiverList.add(RetrieveUserDB.retrieveFromId(14));
    }

    public void getSelectedChoice(ActionEvent event) {
        if (!receiverChoice.getValue().equals("None")) {
            receiverLabel.setVisible(false);
        }
        else {
            receiverChoice.setValue("");
            receiverLabel.setVisible(true);
        }
    }

    public void showMessagesInScrollPane() {
        StringBuilder buildLog = new StringBuilder();
        for (String messages : chat.getMessageLog(user.getId())) {
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
