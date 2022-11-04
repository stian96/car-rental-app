package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;

import java.io.IOException;

public class RegisterCarController {

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
    private ChoiceBox<Integer> chooseModelYear;

    public void RegisterCar(ActionEvent event){
        Main m = new Main();
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
        ){try {
            if(!v.existsInDb()){
                Vehicle c = new Car(regNo,manu,model,engineType,gearType,ConvertIntoNumeric(modelYear), /**gives null for the vehicle fix **/
                        ConvertIntoNumeric(seatingCapacity),ConvertIntoNumeric(towingCapacity));
                        m.changeScene("ToGoPage.fxml");
            }else {registerPrompt.setText("Already Exists");}
        }catch (IOException e){
            System.out.println(e.getMessage());
        }


        }
    else { registerPrompt.setText("enter information");}

}
public void pickYear(ActionEvent event) throws IOException{
        int year = getChooseModelYear().getValue();

}
public void addYearsToCB(ChoiceBox<Integer> chooseModelYear){
        chooseModelYear.setValue(1920);
}

    public ChoiceBox<Integer> getChooseModelYear() {
        return chooseModelYear;
    }

    public void setChooseModelYear(ChoiceBox<Integer> chooseModelYear) {
        this.chooseModelYear = chooseModelYear;
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
}
