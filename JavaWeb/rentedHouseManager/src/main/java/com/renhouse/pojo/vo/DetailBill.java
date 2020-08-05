package com.renhouse.pojo.vo;

import java.math.BigDecimal;

/**
 * @ClassName DetailBill
 * @Description TODO
 * @Author 1TreeForest
 * @Date 2020/7/18 13:05
 * @Version 1.0
 */
public class DetailBill {
    private String date;
    private String houseName;
    private String tenant;
    private BigDecimal monthRent;
    private BigDecimal maintenanceFee;
    private BigDecimal total;

    public DetailBill() {
    }

    public DetailBill(String date, String houseName, String tenant, BigDecimal monthRent, BigDecimal maintenanceFee, BigDecimal total) {
        this.date = date;
        this.houseName = houseName;
        this.tenant = tenant;
        this.monthRent = monthRent;
        this.maintenanceFee = maintenanceFee;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
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

    public BigDecimal getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(BigDecimal maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DetailBill{" +
                "date='" + date + '\'' +
                ", houseName='" + houseName + '\'' +
                ", tenant='" + tenant + '\'' +
                ", monthRent=" + monthRent +
                ", maintenanceFee=" + maintenanceFee +
                ", total=" + total +
                '}';
    }
}
