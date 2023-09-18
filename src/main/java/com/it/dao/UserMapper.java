package com.it.dao;
import com.it.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper{

    @Select("select * from user where user_name=#{name} AND user_password=#{password} AND user_status!='1'")
    @Results(id = "userMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "user_id",property = "id"),
            @Result(column = "user_name",property = "name"),
            @Result(column = "user_password",property = "password"),
            @Result(column = "user_role",property = "role"),
            @Result(column = "user_status",property = "status")
    })
    public User login(User user);


    @Select("select * from user")
    @ResultMap("userMap")
    public List<User> findAllUser();

    @Select("select * from user where user_id = #{id}")
    @ResultMap("userMap")
    public User findUserById(User user);

    @Update("        <script>\n" +
            "        update user\n" +
            "        <set>\n" +
            "            <if test=\"name != null and name.trim()!=''\">\n" +
            "                user_name = #{name},\n" +
            "            </if>\n" +
            "            <if test=\"password != null and password.trim()!=''\">\n" +
            "                user_password = #{password},\n" +
            "            </if>\n" +
            "            <if test=\"role != null and role.trim()!=''\">\n" +
            "                user_role = #{role},\n" +
            "            </if>\n" +
            "            <if test=\"status != null\">\n" +
            "                user_status = #{status}\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where user_id=#{id}\n" +
            "        </script>")
    public Integer editUser(User user);

    @Insert("insert into user(user_name,user_password,user_role,user_status) " +
            "value (#{name},#{password},#{role},#{status})")
    public Integer addUser(User user);

    @Select({"<script>" +
            "SELECT * FROM user " +
            "where 1=1" +
            "<if test=\"name != null\"> AND  user_name  like  CONCAT('%',#{name},'%')</if>" +
            "<if test=\"role != null\"> AND user_role like  CONCAT('%', #{role},'%') </if>" +
            "<if test=\"status != null\"> AND user_status like  CONCAT(#{status},'%')</if>" +
            "</script>"
    })
    @ResultMap("userMap")
    public List<User> userSearch(User user);
}
