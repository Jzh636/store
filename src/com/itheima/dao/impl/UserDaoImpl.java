package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDaoImpl implements UserDao {

    /**
     * 用户注册
     * @param user
     */
    @Override
    public void add(User user) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        /**
         * /*
         *     `uid` varchar(32) NOT NULL,
         *   `username` varchar(20) DEFAULT NULL,
         *   `password` varchar(20) DEFAULT NULL,
         *
         *   `name` varchar(20) DEFAULT NULL,
         *   `email` varchar(30) DEFAULT NULL,
         *   `telephone` varchar(20) DEFAULT NULL,
         *
         *   `birthday` date DEFAULT NULL,
         *   `sex` varchar(10) DEFAULT NULL,
         *   `state` int(11) DEFAULT NULL,
         *   `code` varchar(64) DEFAULT NULL,
         *      */
         String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
         qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
                 user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
    }

    /**
     * 通过激活码获取用户
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public User getByCode(String code) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where code = ? limit 1";
        return qr.query(sql, new BeanHandler<>(User.class), code);
    }

    /**
     * 修改用户信息
     * @param user
     * @throws Exception
     */
    @Override
    public void update(User user) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update user set username = ?, password = ?, name = ?, email = ?,telephone = ?, birthday = ?, sex = ?, state = ?, code = ? where uid = ?";
        qr.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), null, user.getUid());
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User getByUsernameAndPassword(String username, String password) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where username = ? and password = ? limit 1";
        return qr.query(sql, new BeanHandler<>(User.class), username, password);
    }

}
