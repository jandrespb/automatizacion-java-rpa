package com.guru99.demo.models;

public class LoginModel {

    private String emailUser, userName, password, messageRobot;

    // Constructor
    public LoginModel() {
    }

    // Getter and Setters
    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessageRobot() {
        return messageRobot;
    }

    public void setMessageRobot(String messageRobot) {
        this.messageRobot = messageRobot;
    }
}
