package com.java.controller;

import com.java.pojo.Admin;
import com.java.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JlX
 * @create 2022-03-06 14:47
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {
    //切记:在所有的界面层,一定会有业务逻辑层的对象
    @Autowired   //spring帮我们注入对象
    AdminService adminService;

    //实现登判断,并进行相应的跳转
    @RequestMapping("/login")
    public String login(String name , String pwd, HttpServletRequest requeset){
        Admin admin = adminService.login(name , pwd);
        if(admin != null){
            //登录成功
            requeset.setAttribute("admin",admin);
            return "main";
        }else {
            //登录失败
            requeset.setAttribute("errmsg","用户名或密码不正确");
            return "login";
        }
    }
}
