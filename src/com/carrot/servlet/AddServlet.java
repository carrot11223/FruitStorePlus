package com.carrot.servlet;

import com.carrot.dao.FruitDao;
import com.carrot.dao.daoImpl.FruitDaoImpl;
import com.carrot.pojo.Fruit;
import com.carrot.thymeleaf.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add")
public class AddServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        double v = Double.parseDouble(price);
        String weight = req.getParameter("weight");
        double v1 = Double.parseDouble(weight);
        String remark = req.getParameter("remark");
        FruitDao fruitDao = new FruitDaoImpl();
        try {
            fruitDao.add(new Fruit(0,name,v,v1,remark));
          resp.sendRedirect("index");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
