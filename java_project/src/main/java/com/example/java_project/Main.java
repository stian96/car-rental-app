package com.example.java_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.hiof.groupproject.tools.db.ConnectDB;

import java.io.IOException;
import java.sql.Connection;


public class Main extends Application {

    private static Stage stg;


    @Override
    public void start(Stage stage) throws IOException {
       Mediator.getInstance().setStage(stage);
        stage.setResizable(false);
        //Parent parent = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ToGoCar.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FilterCar.fxml"));
        Parent parent = FXMLLoader.load(getClass().getResource("FilterCar.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("ToGoCar");
        stage.setScene(new Scene(parent,600,400));
        stage.show();
    }

    //public void checkConnection(){Connection con = ConnectDB.connect();}

    public void changeScene(String fxml) throws IOException {
        Parent pane;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        pane = loader.load();
        stg.getScene().setRoot(pane);
    }




    public static void main(String[] args) {
        launch();
    }
}