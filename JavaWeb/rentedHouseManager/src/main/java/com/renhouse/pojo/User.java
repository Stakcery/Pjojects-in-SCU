package com.renhouse.pojo;

import java.math.BigDecimal;

public class User {
    private Integer id;
    private String  username;
    private String  password;
    private String  phone;
    private BigDecimal income;

    public User() {
    }

    public User(Integer id, String username, String password, String phone, BigDecimal income) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.income = income;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", income=" + income +
                '}';
    }
}
