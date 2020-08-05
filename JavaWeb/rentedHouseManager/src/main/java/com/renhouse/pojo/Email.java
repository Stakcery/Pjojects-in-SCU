package com.renhouse.pojo;

public class Email {
    private Integer id;
    private String username;
    private String userEmail;
    private String emailKey;
    private String ip;

    public Email() {
    }

    public Email(Integer id, String username, String userEmail, String emailKey, String ip) {
        this.id = id;
        this.username = username;
        this.userEmail = userEmail;
        this.emailKey = emailKey;
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getEmailKey() {
        return emailKey;
    }

    public void setEmailKey(String emailKey) {
        this.emailKey = emailKey;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", emailKey='" + emailKey + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
