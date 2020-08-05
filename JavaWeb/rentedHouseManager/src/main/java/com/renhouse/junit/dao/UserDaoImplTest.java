package com.renhouse.junit.dao;

import com.renhouse.dao.UserDao;
import com.renhouse.dao.impl.UserDaoImpl;
import com.renhouse.pojo.User;
import com.renhouse.utils.MD5Utils;
import org.junit.Test;

import java.math.BigDecimal;

public class UserDaoImplTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUsernameAndPassword("admin","admin") == null){
            System.out.println("用户名或者密码错误，登陆失败");
        }else {
            System.out.println("登陆成功");
        }
    }


    @Test
    public void saveUser() {
        User user = new User(null,"admin23","123456","6111111",new BigDecimal(500));
        UserDao userDao = new UserDaoImpl();
        if (userDao.addUser(user) == -1){
            System.out.println("用户已存在");
        }else {
            System.out.println("保存成功");
        }
    }

    @Test
    public void modifyPassword() {
        UserDao userDao = new UserDaoImpl();
        String password = "wclwcl";
        String toMD5 = MD5Utils.toMD5(password);
        userDao.modifyPassword("wclwcl",toMD5);
    }
}