package com.renhouse.junit.service;

import com.renhouse.service.UserService;
import com.renhouse.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    private UserService userService = new UserServiceImpl();

    @Test
    public void modifyPassword() {
        userService.modifyPassword("admin","admin");
    }
}