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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
/**
 * ViewBaseServlet继承了HttpServlet方法，并且衍生了其他方法 例如processTemplate 这里需要用到
 * 使用Thymeleaf的几个步骤：
 1- 导入相关jar包
 2- 引入模板类，自己写的servlet继承模板类ViewServletBase
 3- 在web.xml语句里面配置上下文参数，配置前缀和后缀
 4- 去物理地址建立相应的.html文件
 */
//从servlet3.0开始，支持注解方式
//注意：@WebServlet("/")匹配这个路径会让css文件失效 迷惑行为，到现在也没搞清楚
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //查询关键字用的是表单，post方法，但是想和分页查询一起做，所以就直接用这个doPost调用一下下面的doGet方法；
        doGet(req,resp);
    }

    /**
     * select count(*) from fruit where name like '%%' ;这种like里面的关键词是空的时候，相当于没有进行模糊查询 返回的是全部
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        int page = 0;
        HttpSession session = request.getSession();
        FruitDao fruitDao = new FruitDaoImpl();
        String search = request.getParameter("search");
        String keyword = null;
        if (StringUtils.isNotEmpty(search)){
            //说明用户点击了的是搜索关键字的按钮,此时关键词就是传过来请求参数的keyword，页码需要是1
        page = 0;//第一次把页码的初始值搞错了，导致一直搜不到关键字
          keyword = request.getParameter("keyword");
         if (StringUtils.isEmpty(keyword)){
             keyword = "";//如果查询的是空的，这是给keyword给一个空字符串
         }
         session.setAttribute("keyword",keyword);
        }else{
            //说明用户是通过非搜索关键字的方式进来的，例如（分页查询等）
            List<Fruit> select = null;
            String pageNo = request.getParameter("pageNo");
            if (StringUtils.isNotEmpty(pageNo)){
                page = Integer.parseInt(pageNo);
            }
          Object  keywordobj = session.getAttribute("keyword");
            if (keywordobj!=null){
                keyword = (String)keywordobj;
            }else {
                keyword = "";
            }
        }
        //到这里if else 都没有进去的话 说明是第一次 默认page = 1；




        try {
            List<Fruit> fruits = fruitDao.pageS(keyword,page);
            session.setAttribute("fruitList",fruits);//只把一页的数据放到了session当中
            session.setAttribute("pageNO",page);
            /**
             * 总记录条数：   总页数：
             * 1             1
             * 2             1
             * 6             2
             * pageCount    (pageCount+5 -1)/5
             */
            //总记录条数
            int pagec = fruitDao.pagecount(keyword);
            int pageCount = (pagec+5 - 1)/5;//总页数
            session.setAttribute("pageCount",pageCount);
            //这里的index是视图名称，Thymeleaf会转换为物理视图名称： prefix+视图名称+suffix
            super.processTemplate("Goods",request,response);
            //response.sendRedirect("/");
        } catch (Exception throwables){
            throwables.printStackTrace();
        }
    }
}
