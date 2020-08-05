package com.renhouse.pojo;

public class Tenant {
    private Integer id;
    private  String tenantName;
    private String tenantPassword;
    private String landlord;
    private String realName;
    private String phoneNumber;
    private String email;

    public Tenant(Integer id, String tenantName, String tenantPassword, String landlord, String realName, String phoneNumber, String email) {
        this.id = id;
        this.tenantName = tenantName;
        this.tenantPassword = tenantPassword;
        this.landlord = landlord;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Tenant() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantPassword() {
        return tenantPassword;
    }

    public void setTenantPassword(String tenantPassword) {
        this.tenantPassword = tenantPassword;
    }

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", tenantName='" + tenantName + '\'' +
                ", tenantPassword='" + tenantPassword + '\'' +
                ", landlord='" + landlord + '\'' +
                ", realName='" + realName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
