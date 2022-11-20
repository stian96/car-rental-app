package com.example.java_project.Controller;

import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterCarController implements Initializable {

    @FXML
    private TextField tf_regNumber;
    @FXML
    private TextField tf_manufacturer;
    @FXML
    private TextField tf_model;
    @FXML
    private TextField tf_EngineType;
    @FXML
    private TextField tf_GearType;
    @FXML
    private TextField tf_ModelYear;
    @FXML
    private TextField tf_SeatingCapacity;
    @FXML
    private TextField tf_TowingCapacity;
    @FXML
    private Button button_Register;
    @FXML
    private Label registerPrompt;
    @FXML
    private Button searchButton;
    @FXML
    private Button button_mainMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_mainMenu.setOnAction(this::mainMenuButton);
        addStyle(button_mainMenu);

        button_Register.setOnAction(this::RegisterCar);
        addStyle(button_Register);
    }

    public void RegisterCar(ActionEvent event){
        String regNo = tf_regNumber.getText().trim();
        String manu = tf_manufacturer.getText().trim();
        String model = tf_model.getText().trim();
        String engineType = tf_EngineType.getText().trim();
        String gearType = tf_GearType.getText().trim();
        String modelYear =tf_ModelYear.getText().trim();

        String seatingCapacity = tf_SeatingCapacity.getText().trim();
        String towingCapacity= tf_TowingCapacity.getText().trim();
        Vehicle v = RetrieveVehicleDB.retrieveFromRegNo(regNo);

        if(!regNo.isEmpty() && !manu.isEmpty() && !model.isEmpty() && !engineType.isEmpty()
        && !gearType.isEmpty() && !modelYear.isEmpty()
                && !seatingCapacity.isEmpty() && !towingCapacity.isEmpty()
        ){
            if(v == null){

            Vehicle c = new Car(regNo,manu,model,engineType,gearType,
                    ConvertIntoNumeric(modelYear), /**gives null for the vehicle fix **/
                    ConvertIntoNumeric(seatingCapacity),ConvertIntoNumeric(towingCapacity));

            button_Register.setText("Success!");
            }
        else {
            registerPrompt.setText("Car exists already");
        }

        }
    else { registerPrompt.setText("Enter information");}

}
    public void mainMenuButton(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("ToGoCar.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    private int ConvertIntoNumeric(String xVal)
    {
        try
        {
            return Integer.parseInt(xVal);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    public void addStyle(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color:  #f1c232; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #f1c232; -fx-text-fill: black"));
    }

    public void searchRegNo(ActionEvent event) {
    }


}
