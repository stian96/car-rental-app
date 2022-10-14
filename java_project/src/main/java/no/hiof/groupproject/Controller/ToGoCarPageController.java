package no.hiof.groupproject.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import no.hiof.groupproject.Main;
import no.hiof.groupproject.tools.VerifyLogInSignUp;

import java.io.IOException;

public class ToGoCarPageController {

    @FXML
    private Button button_registerCar;
    @FXML
    private Button button_bookCar;
    @FXML
    private Button button_profile;
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
