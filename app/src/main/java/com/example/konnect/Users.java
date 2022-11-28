package com.example.konnect;

public class Users {
    private String username;
    private String mail;
    private String password;
    private String profilepic;
    private String lastmessage;
    private String UserId;
    private String status;

    public Users(String username, String lastmessage) {
        this.username = username;
        this.lastmessage = lastmessage;
    }

    public Users(String username, String mail, String password, String profilepic, String lastmessage, String userId) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.profilepic = profilepic;
        this.lastmessage = lastmessage;
        UserId = userId;
    }

    public Users(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }
    public Users(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }


}
