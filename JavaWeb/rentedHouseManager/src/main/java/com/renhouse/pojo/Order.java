package com.renhouse.pojo;

public class Order {

   private Integer id;
   private String orderNo;
   private String orderHouse;
   private String landlord;
   private String tenantName;
   private String orderTime;
   private String phoneNumber;

    public Order() {
    }


    public Order(Integer id, String orderNo, String orderHouse, String landlord, String tenantName, String orderTime, String phoneNumber) {
        this.id = id;
        this.orderNo = orderNo;
        this.orderHouse = orderHouse;
        this.landlord = landlord;
        this.tenantName = tenantName;
        this.orderTime = orderTime;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderHouse() {
        return orderHouse;
    }

    public void setOrderHouse(String orderHouse) {
        this.orderHouse = orderHouse;
    }

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", orderHouse='" + orderHouse + '\'' +
                ", landlord='" + landlord + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
