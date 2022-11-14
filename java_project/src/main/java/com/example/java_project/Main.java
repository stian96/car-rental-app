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
    private Stage stage;


    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;
        stg = stage;

        stage.setResizable(false);
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogIn.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ToGoCar.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FilterCar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //checkConnection();
        stage.setTitle("ToGoCar");
        stage.setScene(scene);
        stage.show();


    }
    public Stage getStage(){
        return stage;
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