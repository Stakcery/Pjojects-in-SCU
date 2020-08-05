package com.renhouse.pojo.vo;

public class TenantMaintenanceFee {
    private Integer id;
    private String tenantName;
    private String realName;
    private String address;
    private String houseName;
    private String phoneNumber;
    private String maintenanceFee;

    public TenantMaintenanceFee() {
    }

    public TenantMaintenanceFee(Integer id, String tenantName, String realName, String address, String houseName, String phoneNumber, String maintenanceFee) {
        this.id = id;
        this.tenantName = tenantName;
        this.realName = realName;
        this.address = address;
        this.houseName = houseName;
        this.phoneNumber = phoneNumber;
        this.maintenanceFee = maintenanceFee;
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

    public String getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(String maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    @Override
    public String toString() {
        return "TenantMaintenanceFee{" +
                "id=" + id +
                ", tenantName='" + tenantName + '\'' +
                ", realName='" + realName + '\'' +
                ", address='" + address + '\'' +
                ", houseName='" + houseName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", maintenanceFee='" + maintenanceFee + '\'' +
                '}';
    }
}
