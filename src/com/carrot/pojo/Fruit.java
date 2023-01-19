package com.carrot.pojo;
import java.util.Objects;


/**
 * 用于数据库的数据映射,一定要和数据库里面的类型匹配，不然拿不到值
 */
public class Fruit {
    //编号
    private Integer id;
    //名称
    private String name;
    //价格
    private Double price;
    //重量/斤
    private Double weight;
    //说明
    private String remark;

    public Fruit() {

    }

    public Fruit(Integer id, String name, Double price, Double weight, String remark) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return weight == fruit.weight &&
                Objects.equals(id, fruit.id) &&
                Objects.equals(name, fruit.name) &&
                Objects.equals(price, fruit.price) &&
                Objects.equals(remark, fruit.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, weight, remark);
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", remark='" + remark + '\'' +
                '}';
    }
}
