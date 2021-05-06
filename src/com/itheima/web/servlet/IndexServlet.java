package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 和首页相关的servlet
 */
@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends BaseServlet {
    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取数据库查询最新的热门商品，将其放入request域中，请求转发
        return "/jsp/index.jsp";
    }

}
