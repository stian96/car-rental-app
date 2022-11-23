package no.hiof.groupproject.gui.Other;

import javafx.stage.Stage;
import no.hiof.groupproject.models.User;

public class Mediator {
    private static Mediator instance;
    private User user;
    private Stage stage;

    public static Mediator getInstance(){
        if(instance == null){
            instance = new Mediator();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void setInstance(Mediator instance) {
        Mediator.instance = instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
