package com.renhouse.dao.impl;

import com.renhouse.dao.BaseDao;
import com.renhouse.dao.UserDao;
import com.renhouse.pojo.User;
import com.renhouse.utils.MD5Utils;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`phone`,`income` from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`phone`,`income` from t_user where username = ? and password = ?";
        String toMD5 = MD5Utils.toMD5(password);
        return queryForOne(User.class, sql, username,toMD5);
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`phone`,`income`) values(?,?,?,?)";
        String toMD5 = MD5Utils.toMD5(user.getPassword());
        return update(sql, user.getUsername(),toMD5,user.getPhone(),user.getIncome());
    }

    @Override
    public int modifyPassword(String username, String password) {
        User user = queryUserByUsername(username);
        String toMD5 = MD5Utils.toMD5(password);
        String sql = "UPDATE t_user SET `password` = ? WHERE username = ?";
        return update(sql, toMD5,user.getUsername());
    }

/*
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(id) from t_user";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<User> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`,`username`,`password`,`phone`,`income` from t_user limit ?,?";
        return queryForList(Tenant.class,sql,begin,pageSize);
    }*/
}
