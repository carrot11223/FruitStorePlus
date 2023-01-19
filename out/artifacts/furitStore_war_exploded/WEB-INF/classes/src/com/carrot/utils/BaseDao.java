package com.carrot.utils;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 该类用来封装DML和DQL的操作
 */
public abstract class BaseDao {
    /**
     * 封装DML方法
     * @param sql 正常的sql语句
     * @param params 可变参数 根据sql的？来处理
     * @return 涉及到的行数
     */
    public static int executeUpdate(String sql,Object... params) throws SQLException {
        //获取连接
        Connection connection = JdbcUtil.getConnection();
        //获取数据库操作对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给sql语句赋值
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i,params[i - 1]);
        }
        //执行sql
        int i = preparedStatement.executeUpdate();
        return i;
    }

    /**
     * 封装DQL语句
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return返回list，返回所有行 也就是一张表 每行都是一个clazz对象
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    //<T>声明一个泛型
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //获取连接
        Connection connection = JdbcUtil.getConnection();
        //获取数据库操作对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.length != 0){//当没有传参的时候，这句显得尤为重要，否则又是一个空指针异常
            //给sql语句赋值
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i,params[i - 1]);
            }
        }


        //执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        //建立一个list 用来存放每一行 每一行都是一个对象
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()){
            T t = clazz.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                //拿到的每一个列值
                Object object = resultSet.getObject(i);
                //拿到的每一个列名
                String columnLabel = metaData.getColumnLabel(i);
                Field declaredField = clazz.getDeclaredField(columnLabel);
                //打破private限制
                declaredField.setAccessible(true);
                declaredField.set(t,object);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            //能够通过if说明不是事务
            JdbcUtil.freeConnection();
        }
        //如果是事务的话，connection不用回收，交给service层管理
        return list;
    }
}
