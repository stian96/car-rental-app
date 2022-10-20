package com.example.java_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import no.hiof.groupproject.Main;

import java.io.IOException;

public class ToGoCarPageController{

    @FXML
    private Button button_registerCar;
    @FXML
    private Button button_bookCar;
    @FXML
    private Button button_profile;

    public ToGoCarPageController(Button button_registerCar, Button button_bookCar, Button button_profile, Button button_messages, Button button_customerService, Button button_logOut) {
        this.button_registerCar = button_registerCar;
        this.button_bookCar = button_bookCar;
        this.button_profile = button_profile;
        this.button_messages = button_messages;
        this.button_customerService = button_customerService;
        this.button_logOut = button_logOut;
    }

    public Button getButton_registerCar() {
        return button_registerCar;
    }

    public void setButton_registerCar(Button button_registerCar) {
        this.button_registerCar = button_registerCar;
    }

    public Button getButton_bookCar() {
        return button_bookCar;
    }

    public void setButton_bookCar(Button button_bookCar) {
        this.button_bookCar = button_bookCar;
    }

    public Button getButton_profile() {
        return button_profile;
    }

    public void setButton_profile(Button button_profile) {
        this.button_profile = button_profile;
    }

    public Button getButton_messages() {
        return button_messages;
    }

    public void setButton_messages(Button button_messages) {
        this.button_messages = button_messages;
    }

    public Button getButton_customerService() {
        return button_customerService;
    }

    public void setButton_customerService(Button button_customerService) {
        this.button_customerService = button_customerService;
    }

    public Button getButton_logOut() {
        return button_logOut;
    }

    public void setButton_logOut(Button button_logOut) {
        this.button_logOut = button_logOut;
    }

    @FXML
    private Button button_messages;
    @FXML
    private Button button_customerService;
    @FXML
    private Button button_logOut;


    public void userRegisterCar(ActionEvent event) throws IOException {
        registerAcar();
    }

    public void userBookCar(ActionEvent event) throws IOException{
        bookAcar();
    }

    public void userProfile(ActionEvent event) throws IOException{
        user_profile();
    }

    public void message_menu(ActionEvent event) throws IOException{
        MessageCheck();
    }
    public void customerService(ActionEvent event) throws IOException{
        CustomerServicePage();
    }
    public void userLogOut(ActionEvent event) throws IOException{
        LogOutCheck();
    }


    private void registerAcar() throws IOException{
        Main m = new Main();
        m.changeScene("logIn.fxml");
    }
    private void bookAcar() throws IOException{
        Main m = new Main();
        m.changeScene("logIn.fxml");
    }
    private void user_profile() throws IOException{
        Main m = new Main();
        m.changeScene("logIn.fxml");
    }
    private void MessageCheck() throws IOException{
        Main m = new Main();
        m.changeScene("logIn.fxml");
    }
    private void CustomerServicePage() throws IOException{
        Main m = new Main();
        m.changeScene("logIn.fxml");
    }

    private void LogOutCheck() throws IOException{
        Main m = new Main();
        m.changeScene("logIn.fxml");
    }

}
