package no.hiof.groupproject.tools;

import java.util.ArrayList;

public class Notification {
    public String newUser; //This should be a user class
    public ArrayList<String> messages = new ArrayList<>();

    /** A method that sends out a notification mail
     * to a new user who creates an account in the system,
     * and to the GUI itself.*/

    public String sendNotification(String newUser, String userEmailAddress){
        String message = messages.get(0);
        if( newUser != null){
            sendToMailAddress(userEmailAddress, message);
        }
        return  sendToGUI(message);
    }
    /**
     * Method that simulates sending a notification
     * to a specified email.
     *
     */
    public String sendToMailAddress(String emailAddress, String message){
        System.out.println("Sending message '" + message +
                "' to email address: " + emailAddress);

        return emailAddress;
    }
    /** Method that returns a message to the GUI*/
    public String sendToGUI(String message){
        return message;
    }
    /** Method that adds messeges to the ArrayList*/

    public void addMessages(String message){
        messages.add(message);
    }
}
