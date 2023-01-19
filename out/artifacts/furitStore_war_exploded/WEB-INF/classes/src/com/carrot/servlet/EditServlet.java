package com.carrot.servlet;

import com.carrot.dao.FruitDao;
import com.carrot.dao.daoImpl.FruitDaoImpl;
import com.carrot.pojo.Fruit;
import com.carrot.thymeleaf.ViewBaseServlet;
import com.carrot.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/edit")
public class EditServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   //request.getParameter("test"); get和post都可用
        String id = req.getParameter("id");
        if (StringUtils.isNotEmpty(id)){//如字符串不为空
            FruitDao fruitDao = new FruitDaoImpl();
            try {
                Fruit select = fruitDao.select(Integer.parseInt(id));
                req.setAttribute("fruit",select);
                //转到/edit.html的地方，多亏了thymeleaf 将数据库中查出来的东西渲染到前端页面上
                super.processTemplate("edit",req,resp);
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }



    }
}
