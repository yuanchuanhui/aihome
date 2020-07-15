package com.glodon.aihome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping
    @ResponseBody
    public String login(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password){
        if(userName.equals("张三") && password.equals("李四")){
            return "success";
        }
        return "fail";
    }

}
