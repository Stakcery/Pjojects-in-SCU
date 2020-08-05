package com.renhouse.service.impl;

import com.renhouse.dao.UserDao;
import com.renhouse.dao.impl.UserDaoImpl;
import com.renhouse.pojo.User;
import com.renhouse.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {

        if (userDao.queryUserByUsername(username) == null) {
           // 等于null,说明没查到，没查到表示可用
           return false;
        }

        return true;

    }

    @Override
    public int modifyPassword(String username, String password) {
        return userDao.modifyPassword(username,password);
    }
}
