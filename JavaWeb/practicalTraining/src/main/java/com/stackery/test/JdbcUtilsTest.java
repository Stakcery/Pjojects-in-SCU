package com.stackery.test;

import com.stackery.utils.JdbcUtils;

import java.sql.Connection;

public class JdbcUtilsTest {

    @org.junit.Test
    public void JdbcTest() {
        Connection connection = JdbcUtils.getConnection();
        JdbcUtils.close(connection);
    }
}