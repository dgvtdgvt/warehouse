package com.it.dao;

import com.it.pojo.Operations;
import com.it.pojo.Warehouse;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OperationsMapper {
    @Select("SELECT * FROM operations")
    @Results(id = "operationsMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "operations_id",property = "id"),
            @Result(column = "operations_product_id",property = "product_id"),
            @Result(column = "operations_product_name",property = "product_name"),
            @Result(column = "operations_product_ean",property = "product_ean"),
            @Result(column = "operations_product_price",property = "product_price"),
            @Result(column = "operations_product_specification",property = "product_specification"),
            @Result(column = "operations_quantity",property = "quantity"),
            @Result(column = "operations_warehouse_name",property = "warehouse_name"),
            @Result(column = "operations_type",property = "type"),
            @Result(column = "operations_user",property = "user"),
            @Result(column = "operations_time",property = "time"),
    })
    public List<Operations> findOperations();

    @Insert("insert into operations" +
            "(operations_product_id," +
            "operations_product_name," +
            "operations_product_ean," +
            "operations_product_price," +
            "operations_product_specification," +
            "operations_quantity," +
            "operations_warehouse_name," +
            "operations_type," +
            "operations_user," +
            "operations_time)" +
            "value(#{product_id}," +
            "#{product_name}," +
            "#{product_ean}," +
            "#{product_price}," +
            "#{product_specification}," +
            "#{quantity}," +
            "#{warehouse_name}," +
            "#{type}," +
            "#{user}," +
            "#{time})")
    public Integer addOperations(Operations operations);

    @Select({"<script>" +
            "SELECT * FROM operations " +
            "where 1=1" +
            "<if test=\"product_name != null\"> AND  operations_product_name  like  CONCAT('%',#{product_name},'%')</if>" +
            "<if test=\"product_ean != null\"> AND operations_product_ean like  CONCAT('%', #{product_ean},'%') </if>" +
            "<if test=\"warehouse_name != null\"> AND operations_warehouse_name like  CONCAT('%',#{warehouse_name},'%')</if>" +
            "<if test=\"user != null\"> AND operations_user like  CONCAT('%',#{user},'%')</if>" +
            "<if test=\"type != null\"> AND operations_type like  CONCAT('%',#{type},'%')</if>" +
            "</script>"
    })
    @ResultMap("operationsMap")
    public List<Operations> operationsSearch(Operations operations);
}
