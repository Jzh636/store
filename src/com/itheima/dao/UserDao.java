package com.itheima.dao;

import com.itheima.domain.User;

public interface UserDao {
    void add(User user) throws Exception;

    User getByCode(String code) throws Exception;

    void update(User user) throws Exception;

    User getByUsernameAndPassword(String username, String password) throws Exception;

}
