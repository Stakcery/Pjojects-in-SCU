package com.renhouse.pojo.vo;

public class HouseStatus {
    private String tenantName;
    private String realName;
    private String address;
    private String houseName;
    private String phoneNumber;
    private String email;

    public HouseStatus() {
    }

    public HouseStatus(String tenantName, String realName, String address, String houseName, String phoneNumber, String email) {
        this.tenantName = tenantName;
        this.realName = realName;
        this.address = address;
        this.houseName = houseName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
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
        return "HouseStatus{" +
                "tenantName='" + tenantName + '\'' +
                ", realName='" + realName + '\'' +
                ", address='" + address + '\'' +
                ", houseName='" + houseName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}