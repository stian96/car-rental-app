package com.example.java_project.Controller.Profile;

public final class UserLogInSession {
    private static UserLogInSession instance;
    private String userEmail;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private UserLogInSession(String userEmail) {
        this.userEmail = userEmail;
    }

    public static UserLogInSession getInstance(String userEmail) {
        if (instance == null) {
            instance = new UserLogInSession(userEmail);
        }
        return instance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void cleaneUserLogInSession() {
        userEmail = null;
    }

    @Override
    public String toString() {
        return userEmail;
    }

}
