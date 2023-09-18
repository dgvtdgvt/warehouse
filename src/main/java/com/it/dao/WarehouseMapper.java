package com.it.dao;

import com.it.pojo.Warehouse;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WarehouseMapper {
    @Select("select * from warehouse")
    @Results(id = "warehouseMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "warehouse_id",property = "id"),
            @Result(column = "warehouse_product_id",property = "product_id"),
            @Result(column = "warehouse_product_name",property = "product_name"),
            @Result(column = "warehouse_product_ean",property = "product_ean"),
            @Result(column = "warehouse_product_price",property = "product_price"),
            @Result(column = "warehouse_product_specification",property = "product_specification"),
            @Result(column = "warehouse_product_quantity",property = "product_quantity"),
            @Result(column = "warehouse_name",property = "name")
    })
    public List<Warehouse> findWarehouse();

    @Select("select * from warehouse where warehouse_product_id=#{product_id} and warehouse_name=#{name}")
    @ResultMap("warehouseMap")
    public Warehouse findWarehouseByproductIdAndName(Warehouse warehouse);

    @Select("select * from warehouse where warehouse_id=#{id}")
    @ResultMap("warehouseMap")
    public Warehouse findWarehouseById(Warehouse warehouse);


    @Update("update warehouse set warehouse_product_quantity=#{product_quantity} where warehouse_id=#{id}")
    public Integer updateProduct(Warehouse warehouse);

    @Insert("insert into warehouse" +
            "(warehouse_product_id," +
            "warehouse_product_name," +
            "warehouse_product_ean," +
            "warehouse_product_price," +
            "warehouse_product_specification," +
            "warehouse_product_quantity," +
            "warehouse_name) " +
            "value (#{product_id}," +
            "#{product_name}," +
            "#{product_ean}," +
            "#{product_price}," +
            "#{product_specification}," +
            "#{product_quantity}," +
            "#{name})")
    public Integer addWarehouse(Warehouse warehouse);

    @Select({"<script>" +
            "SELECT * FROM warehouse " +
            "where 1=1" +
            "<if test=\"product_name != null\"> AND  warehouse_product_name  like  CONCAT('%',#{product_name},'%')</if>" +
            "<if test=\"product_ean != null\"> AND warehouse_product_ean like  CONCAT('%', #{product_ean},'%') </if>" +
            "<if test=\"name != null and name !=''\"> AND warehouse_name =#{name}</if>" +
            "<if test=\"product_quantity != null and product_quantity ==1\"> AND warehouse_product_quantity !=0</if>" +
            "<if test=\"product_quantity != null and product_quantity ==0\"> AND warehouse_product_quantity =0</if>" +
            "</script>"
    })
    @ResultMap("warehouseMap")
    public List<Warehouse> warehouseSearch(Warehouse warehouse);
}
