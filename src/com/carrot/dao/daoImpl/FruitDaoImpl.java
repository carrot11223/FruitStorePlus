package com.carrot.dao.daoImpl;
import com.carrot.dao.FruitDao;
import com.carrot.pojo.Fruit;
import com.carrot.utils.BaseDao;
import com.carrot.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 该实现类实现了FruitDao的方法
 */
public class FruitDaoImpl extends BaseDao implements FruitDao {
    @Override
    public void add(Fruit fruit){
        //可以把id的值设置为null或者0，这样子mysql都会自己做处理
        String sql = "insert into fruit values(0,?,?,?,?)";
        try {
            super.executeUpdate(sql,fruit.getName(),fruit.getPrice(),fruit.getWeight(),fruit.getRemark());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void del(Integer id){
        String sql = "delete from fruit where id = ?";
        try {
            super.executeUpdate(sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Fruit fruit){
       String sql = "update fruit set name = ?,price = ?,weight = ?,remark = ? where id = ?";
        try {
            super.executeUpdate(sql,fruit.getName(),fruit.getPrice(),fruit.getWeight(),fruit.getRemark(),fruit.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Fruit select(Integer id) {
        String sql = "select * from fruit where id = ?";
        List<Fruit> fruits = null;
        try {
            fruits = super.executeQuery(Fruit.class, sql, id);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return fruits.get(0);
    }

    @Override
    public List<Fruit> select() {
       String sql = "select * from fruit";
        List<Fruit> fruits = null;
        try {
            fruits = super.executeQuery(Fruit.class, sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return fruits;
    }

    @Override
    public List<Fruit> pageSelect(Integer pageNo) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql = "select * from fruit limit ? , 5";//从?开始数，数五个出来放到页面上
        List<Fruit> fruits = super.executeQuery(Fruit.class, sql, (pageNo-1)*5);//pageNo指的是第几页，刚开始第一页，带入sql就是从0后面开始数五个数出来
        return fruits;
    }

    @Override
    public int pagecount() throws SQLException {
        //获取链接
        Object object = null;
        int rowCount = 0;
        Connection connection = JdbcUtil.getConnection();
        String sql = "select count(*) from fruit";
        //获取数据库操作对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
                rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    @Override
    public List<Fruit> pageS(String keyword, Integer pageNo) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql = "select * from fruit where name like ? or remark like ? limit ? , 5";
        List<Fruit> fruits = super.executeQuery(Fruit.class, sql, "%" + keyword + "%", "%" + keyword + "%", pageNo);
        return fruits;
    }

    @Override
    public int pagecount(String keyword) throws SQLException {
        //获取链接
        int rowCount = 0;
        Connection connection = JdbcUtil.getConnection();
        String sql = "select count(*) from fruit where name like ? or remark like ?";
        //获取数据库操作对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,keyword);
        preparedStatement.setObject(2,keyword);
        //执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }
}
