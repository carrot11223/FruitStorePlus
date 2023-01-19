package com.carrot.dao;

import com.carrot.pojo.Fruit;

import java.sql.SQLException;
import java.util.List;

/**
 * 负责增删改查的接口
 */
public interface FruitDao {

    //新增一个水果，这里servlet封装成Fruit对象穿进来
    public abstract void add(Fruit fruit) throws SQLException;//接口当中如没有用public abstract修饰，可以有方法体

    //删除水果，通过传入的水果的id进行删除
    public abstract void del(Integer id) throws SQLException;

    //写到这里的时候死去的记忆突然复活了，接口是用public abstract修饰的 ，一般都不写 只写后面的void....,呜呜这都忘了？
    //修改水果信息
    void update(Fruit fruit) throws SQLException;

    //根据id查询水果信息
    Fruit select(Integer id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    //查询所有水果的信息
    List<Fruit> select() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

   //分页查询
    List<Fruit> pageSelect(Integer pageNo) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    int pagecount() throws SQLException;

    //下面这两个方法的产生是因为想加入关键字模块，就会对导致包含关键字的 总页数会改变，所以有了pagecount（），另外分页查询也受到了影响；
    List<Fruit> pageS(String keyword,Integer pageNo) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    int pagecount(String keyword) throws SQLException;
}
