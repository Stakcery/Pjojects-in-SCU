package com.renhouse.junit.dao;

import com.renhouse.dao.EmailDao;
import com.renhouse.dao.impl.EmailDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailDaoImplTest {

    @Test
    public void queryEmailById() {
        EmailDao emailDao = new EmailDaoImpl();
        System.out.println(emailDao.queryEmailById(1));
    }
}