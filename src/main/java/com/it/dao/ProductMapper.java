package com.it.dao;

import com.github.pagehelper.Page;
import com.it.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductMapper {

    @Select("SELECT * FROM product")
    @Results(id = "productMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "product_id",property = "id"),
            @Result(column = "product_name",property = "name"),
            @Result(column = "product_ean",property = "ean"),
            @Result(column = "product_price",property = "price"),
            @Result(column = "product_specification",property = "specification"),
    })
    public List<Product> selectProduct();

    @Select("select * from product where product_id=#{id}")
    @ResultMap("productMap")
    public Product findProductById(Product product);


    @Update("        <script>\n" +
            "        update product\n" +
            "        <set>\n" +
            "            <if test=\"name != null and name.trim()!=''\">\n" +
            "                product_name = #{name},\n" +
            "            </if>\n" +
            "            <if test=\"ean != null and ean.trim()!=''\">\n" +
            "                product_ean = #{ean},\n" +
            "            </if>\n" +
            "            <if test=\"price != null and price.trim()!=''\">\n" +
            "                product_price = #{price},\n" +
            "            </if>\n" +
            "            <if test=\"specification != null and specification.trim()!=''\">\n" +
            "                product_specification = #{specification}\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where product_id=#{id}\n" +
            "        </script>")
    public Integer editProduct(Product product);

    @Insert("insert into product(product_name,product_ean,product_price,product_specification)" +
            "value (#{name},#{ean},#{price},#{specification})")
    public Integer addProduct(Product product);

    @Select({"<script>" +
            "SELECT * FROM product " +
            "where 1=1" +
            "<if test=\"name != null\"> AND  product_name  like  CONCAT('%',#{name},'%')</if>" +
            "<if test=\"ean != null\"> AND product_ean like  CONCAT('%', #{ean},'%') </if>" +
            "<if test=\"price != null\"> AND product_price like  CONCAT(#{price},'%')</if>" +
            "</script>"
    })
    @ResultMap("productMap")
    public List<Product> productSearch(Product product);
}
