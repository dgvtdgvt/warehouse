package com.it.service.impl;

import com.it.dao.UserMapper;
import com.it.pojo.User;
import com.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User findUserById(User user) {
        return userMapper.findUserById(user);
    }

    @Override
    public Integer editUser(User user) {
        return userMapper.editUser(user);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<User> userSearch(User user) {
        return userMapper.userSearch(user);
    }
}
