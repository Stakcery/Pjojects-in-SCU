package com.renhouse.junit.utils;

import com.renhouse.utils.TimeUtils;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Date;



public class TimeUtilsTest {

    @Test
    public void getDateFromString() {
        Date t1 = TimeUtils.getDateFromString("1111-12-30");
        System.out.println(t1);
    }

    @Test
    public void getFormatDate() {
        System.out.println(TimeUtils.getFormatDate(new Date()));
    }

    @Test
    public void isInStartEnd() {
        boolean t1 = TimeUtils.isInStartEnd("1000-1-2", "1000-1-1", "1000-1-3");
        boolean t2 = TimeUtils.isInStartEnd("1000-1-0", "1000-1-1", "1000-1-3");
        System.out.println(t1);
        System.out.println(t2);
    }

    @Test
    public void getInterval() {
        int t1 = TimeUtils.getInterval("2000-1-1", "2022-7-1");
        System.out.println(t1);
    }

    @Test
    public void getDateAdd() {
        String t1 = TimeUtils.getDateAdd("1000-1-1",3);
        System.out.println(t1);
    }

    @Test
    public void getCurrentDateStr() {
        String t1 = TimeUtils.getCurrentDateStr();
        System.out.println(t1);
    }

    @Test
    public void getCurrentDate() {
        Date t1 = TimeUtils.getCurrentDate();
        System.out.println(t1);
    }

    @Test
    public void getFormatCurrentAdd() {
        String t1 = TimeUtils.getFormatCurrentAdd(30);
        System.out.println(t1);
    }

    @Test
    public void getFormatDateAdd() {
        String t1 = TimeUtils.getFormatDateAdd("2000-1-1",31);
        System.out.println(t1);
    }
    @Test
    public void getIntervalDays(){
        System.out.println(TimeUtils.getIntervalDays("2000-1-1","2000-1-30"));
    }
}