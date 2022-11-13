package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.RetrieveUserDB;


import java.io.IOException;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

public class LogInController {


    @FXML
    protected Label wrongLogin;
    @FXML
    protected TextField tf_userName;
    @FXML
    protected PasswordField tf_password;
    @FXML
    protected Button button_logIn;
    @FXML
    protected Button button_signUp;

    public LogInController() {
        super();

    }

    public void userLogIn(ActionEvent event) throws IOException {
        Main m = new Main();
        User u = RetrieveUserDB.retrieveFromEmail(tf_userName.getText());
        String password = tf_password.getText();
        String email = tf_userName.getText();

        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                if (!u.existsInDb()) {
                    SignUpCheck();
                } else if (u.getEmail().equals(email) && u.getPassword().equals(password)) {

                    Mediator.getInstance().setUser(new User(email,password));

                    Parent pane = FXMLLoader.load(getClass().getResource("ToGoCar.fxml"));
                   //
                    //ToGoCarPageController c = loader.getController();
                    //c.displayName(firstName());

                  Stage stage1 = Mediator.getInstance().getStage();
                  stage1.setScene(new Scene(pane));
                    //stage.show();



                } else {
                    wrongLogin.setText("Wrong email or password");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void userSignUp(ActionEvent event) throws IOException {
        SignUpCheck();
    }

    public String firstName(){
        User u = RetrieveUserDB.retrieveFromEmail(tf_userName.getText());
        return u.getFirstName();

    }
/*
    private Stage stage;
    private Scene scene;
    private Parent pane;
    public void changeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ToGoCar.fxml"));
        pane = loader.load();
        ToGoCarPageController c = loader.getController();
        c.displayName(firstName());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

 */

    private void SignUpCheck() throws IOException {
        Main m = new Main();
        m.changeScene("SignUp.fxml");

    }

}
