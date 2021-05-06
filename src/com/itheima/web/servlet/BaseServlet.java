package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 通用servlet
 */
@WebServlet(name = "BaseServlet", urlPatterns = "/base")
public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {//1、获取子类，找到要执行的哪个servlet
            Class clazz = this.getClass();
            System.out.println(clazz);
            //2、获取请求的方法（即servlet里面调用的哪个方法）
            String m = request.getParameter("method");
            if(m == null){
                m = "index";
            }
            System.out.println(m);
            //3、获取方法对象
            Method method = clazz.getMethod(m, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println(method.equals(null) + "===============================");
            //4、方法执行,返回值是请求转发的路径
            String s = (String) method.invoke(clazz.newInstance(), request, response);
            System.out.println("service 路径为空");
            //5、判断s是否为空
            if(s != null){
                request.getRequestDispatcher(s).forward(request, response);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

/*    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
}
