package com.it.service;

import com.it.pojo.User;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface UserService {
    public User login(User user);

    public List<User> findAllUser();

    public User findUserById(User user);

    public Integer editUser(User user);

    public Integer addUser(User user);

    public List<User> userSearch(User user);
}
