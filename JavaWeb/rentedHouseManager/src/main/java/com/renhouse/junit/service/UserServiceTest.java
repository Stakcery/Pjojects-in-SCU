package com.renhouse.junit.service;

import com.renhouse.pojo.User;
import com.renhouse.service.UserService;
import com.renhouse.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "bbj168", "666666", "111111", null));
    }

    @Test
    public void login() {
        System.out.println( userService.login(new User(null, "wzg168", "123456", "222222",null)) );
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("admin")) {
            System.out.println("用户名已存在！");
        } else {
            System.out.println("用户名可用！");
        }
    }
}