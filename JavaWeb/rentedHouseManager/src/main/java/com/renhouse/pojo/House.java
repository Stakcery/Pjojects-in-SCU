package com.renhouse.pojo;

import java.math.BigDecimal;

public class House {
    private Integer id;
    private String landlord;
    private String tenant;
    private BigDecimal monthRent;
    private Integer space;
    private String rentalStatus;
    private String address;
    private String layout;
    private String endTime;
    private String startTime;
    private String houseName;
    private BigDecimal maintenanceFee;

    public House() {
    }

    public House(Integer id, String landlord, String tenant, BigDecimal monthRent, Integer space, String rentalStatus, String address, String layout, String endTime, String startTime, String houseName, BigDecimal maintenanceFee) {
        this.id = id;
        this.landlord = landlord;
        this.tenant = tenant;
        this.monthRent = monthRent;
        this.space = space;
        this.rentalStatus = rentalStatus;
        this.address = address;
        this.layout = layout;
        this.endTime = endTime;
        this.startTime = startTime;
        this.houseName = houseName;
        this.maintenanceFee = maintenanceFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public BigDecimal getMonthRent() {
        return monthRent;
    }

    public void setMonthRent(BigDecimal monthRent) {
        this.monthRent = monthRent;
    }

    public Integer getSpace() {
        return space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public BigDecimal getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(BigDecimal maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", landlord='" + landlord + '\'' +
                ", tenant='" + tenant + '\'' +
                ", monthRent=" + monthRent +
                ", space=" + space +
                ", rentalStatus='" + rentalStatus + '\'' +
                ", address='" + address + '\'' +
                ", layout='" + layout + '\'' +
                ", endTime='" + endTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", houseName='" + houseName + '\'' +
                ", maintenanceFee='" + maintenanceFee + '\'' +
                '}';
    }
}


