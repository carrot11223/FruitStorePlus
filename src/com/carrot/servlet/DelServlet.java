package com.carrot.servlet;

import com.carrot.dao.FruitDao;
import com.carrot.dao.daoImpl.FruitDaoImpl;
import com.carrot.thymeleaf.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/del")
public class DelServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        FruitDao fruitDao = new FruitDaoImpl();
        try {
            fruitDao.del(i);
            resp.sendRedirect("index");//重定向的时候不要写成/index 否则url就会使8081/index....
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
