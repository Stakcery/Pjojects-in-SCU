package com.renhouse.dao;

import com.renhouse.pojo.Email;

public interface EmailDao {

    /**
     * 根据id查邮箱信息
     * @param id
     * @return
     */
    public Email queryEmailById(Integer id);

}
