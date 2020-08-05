package com.renhouse.pojo.vo;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * @ClassName Bill
 * @Description Bill的vo
 * @Author 1TreeForest
 * @Date 2020/7/16 16:05
 * @Version 1.0
 */
public class Bill {
    private String date;
    private BigDecimal monthRent;
    private BigDecimal maintenanceFee;
    private BigDecimal total;

    public Bill() {
    }

    public Bill(String date, BigDecimal monthRent, BigDecimal maintenanceFee, BigDecimal total) {
        this.date = date;
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
        return "Bill{" +
                "date='" + date + '\'' +
                ", monthRent=" + monthRent +
                ", maintenanceFee=" + maintenanceFee +
                ", total=" + total +
                '}';
    }


}
