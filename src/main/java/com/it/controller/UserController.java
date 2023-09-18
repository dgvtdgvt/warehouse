package com.it.controller;

import com.it.entity.Result;
import com.it.pojo.User;
import com.it.security.StringToMd5;
import com.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping("/findUserById")
    @ResponseBody
    public Result<User> findUserById(Integer user_id){
        User user = new User();
        user.setId(user_id);
        user = userService.findUserById(user);
        return new Result<>(true,"",user);
    }

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        user.setPassword(StringToMd5.getMd5Str(user.getPassword()));
        System.out.println(user);
        User user1 = userService.login(user);
//        System.out.println(user1);
        if (user1==null){
            request.setAttribute("msg","用户名或密码错误");
            return "login";
        }else {
            request.getSession().setAttribute("USER",user1);
            return "redirect:/pages/main.jsp";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "login";
    }

    @RequestMapping("/user")
    public ModelAndView findAllUser(){
        List<User> users = userService.findAllUser();
//        System.out.println(users);
        modelAndView.addObject("users",users);
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public Result editUser(User user){
        System.out.println(user);
        if (user.getName()==""){
            return new Result(false, "修改用户失败!用户名不能为空");
        }
//            密码为空默认不修改
        if (user.getPassword()!=""){
            user.setPassword(StringToMd5.getMd5Str(user.getPassword()));
        }

        List<User> users = userService.findAllUser();
        for (User u:users) {
//            System.out.println(u);
            if (u.getName().equals(user.getName())&&u.getId()!=user.getId()){
                return new Result(false, "修改用户失败!用户名已存在");
            }
        }
        int num = userService.editUser(user);
        if(num != 1){
            return new Result(false, "修改用户失败!");
        }else {
            return new Result(true, "修改用户成功!");
        }
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Result addUser(User user){

        if (user.getPassword()=="" || user.getName()==""){
            return new Result(false, "新增用户失败!用户名或密码不能为空");
        }
        user.setPassword(StringToMd5.getMd5Str(user.getPassword()));
//        System.out.println(user);
        List<User> users = userService.findAllUser();
        for (User u:users) {
//            System.out.println(u);
            if (u.getName().equals(user.getName())){
                return new Result(false, "新增用户失败!用户名已存在");
            }
        }

        int num = userService.addUser(user);
        if(num != 1){
            return new Result(false, "添加用户失败!");
        }else {
            return new Result(true, "添加用户成功!");
        }
    }

    @RequestMapping("/userSearch")
    public ModelAndView userSearch(User user){
        System.out.println(user);

        if(user.getRole().equals("管理员")){
            user.setRole("ADMIN");
        }else if(user.getRole().equals("用户")){
            user.setRole("USER");
        }else {
            user.setRole("");
        }
        if(user.getPassword().equals("正常")){
            user.setStatus(0);
        }else if(user.getPassword().equals("禁用")){
            user.setStatus(1);
        }else {
            user.setStatus(null);
        }
        List<User> users = userService.userSearch(user);
        System.out.println(user);
        modelAndView.addObject("users",users);
        modelAndView.addObject("search",user);
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
