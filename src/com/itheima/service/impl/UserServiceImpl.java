package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.MailUtils;

public class UserServiceImpl implements UserService {
    /**
     *用户注册
     * @param user
     */
    @Override
    public void regist(User user) throws Exception {
        UserDao dao = new UserDaoImpl();
        dao.add(user);
        //发送邮件
        String emailMsg = "欢迎您注册成为我们的一员，<a href='http://localhost/store/user?method=active&code="+user.getCode()+"'>点此激活</a>";
        //收件人 邮件内容
        MailUtils.sendMail(user.getEmail(), emailMsg);
    }

    /**
     * 用户激活
     * @param code
     * @return
     */
    @Override
    public User active(String code) throws Exception {
        UserDao dao = new UserDaoImpl();
        //1、通过code获取一个用户
        User user = dao.getByCode(code);
        System.out.println("用户名" + user.getUsername());
        //2、判断用户是否为空
        if(user == null){
            return null;
        }
        //3、修改用户状态
        //设置用户的状态为1
        user.setState(1);
        dao.update(user);
        return user;
    }
}
