package com.carrot.utils;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 该类主要为jdbc数据库连接的前两步骤和最后一步：注册驱动，获取连接，释放资源
 */
public class JdbcUtil {
    static DataSource dataSource = null;
    static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    /**
     * 静态代码块，当类加载时建立连接池
     */
    static {
        Properties properties = new Properties();
        try {

            InputStream resourceAsStream = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
             properties.load(resourceAsStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接的方法
     */
    public static Connection getConnection() throws SQLException {

        Connection connection = threadLocal.get();
        if (connection==null) {
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
         return connection;
    }
    /**
     * 回收连接的方法
     */
    public static void freeConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection!=null) {
            threadLocal.remove();
            //如果开启了事务，关闭
            connection.setAutoCommit(true);
            connection.close();
        }
    }
}
