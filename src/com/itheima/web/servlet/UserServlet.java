package com.itheima.web.servlet;

import com.itheima.domain.User;
import com.itheima.myconverter.MyConverter;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.utils.MD5Utils;
import com.itheima.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 和用户相关的servlet
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet {
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("userservlet 的add方法执行了");
        return null;
    }

    /**
     * 跳转到注册页面
     */
    public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1、封装数据
        User user = new User();
        //注册转化器
        ConvertUtils.register(new MyConverter(), Date.class);
        BeanUtils.populate(user, request.getParameterMap());
        //1.1设置用户id
        user.setUid(UUIDUtils.getId());
        //1.2设置激活码
        user.setCode(UUIDUtils.getCode());
        //1.3加密密码
        user.setPassword(MD5Utils.md5(user.getPassword()));
        //2、调用service完成注册
        UserService s = new UserServiceImpl();
        s.regist(user);
        //3、页面请求转发
        request.setAttribute("msg", "用户注册已成功，请到邮箱激活~~~");
        return "/jsp/msg.jsp";
    }

    /**
     * 用户激活
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1、获取激活码
        String code = request.getParameter("code");
        //2、调用service完成激活
        UserService s = new UserServiceImpl();
        User user = s.active(code);
        //通过激活码没有找到用户
        if(user == null){
            System.out.println("active 用户不存在");
            request.setAttribute("msg", "请重新激活");
        }else {
            System.out.println("active 用户存在");
            //添加信息
            request.setAttribute("msg", "激活成功");
        }
        //3、请求转发到msg.jsp
        return "/jsp/msg.jsp";
    }

}
