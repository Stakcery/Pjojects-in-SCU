package com.renhouse.dao.impl;

import com.renhouse.dao.BaseDao;
import com.renhouse.dao.EmailDao;
import com.renhouse.pojo.Email;

public class EmailDaoImpl extends BaseDao implements EmailDao {

    @Override
    public Email queryEmailById(Integer id) {
        String sql = "select id,username,userEmail,emailKey,ip from t_email where id = ?";
        return queryForOne(Email.class,sql,id);
    }
}
