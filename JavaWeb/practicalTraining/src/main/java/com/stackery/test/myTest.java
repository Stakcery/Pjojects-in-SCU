package com.stackery.test;

import com.stackery.utils.JdbcUtils;

import java.sql.Connection;

public class myTest {
    public static void main(String[] args) {
        Connection connection = JdbcUtils.getConnection();
        JdbcUtils.close(connection);
    }
}
