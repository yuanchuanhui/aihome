package com.glodon.aihome.controller;

import com.glodon.aihome.entity.NutritionOfDay;
import com.glodon.aihome.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private User user;

    @RequestMapping("login")
    @ResponseBody
    public String login(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password){
        if(userName.equals("admin") && password.equals("123456")){
            user.setUserName(userName);
            user.setPassword(password);
            return "success";
        }
        return "fail";
    }

    /**
     * 返回值为该用户的营养摄入历史，为map<Integer, NutritionOfDay>类型，NutritionOfDay包括一个map，该map包含营养-比例键值对
     */
    @RequestMapping("getHistory")
    @ResponseBody
    public Map<Integer, NutritionOfDay> getHistory(){
        return user.getMeals();
    }

    @RequestMapping("updateUserInfo")
    @ResponseBody
    public boolean updateUserInfo(Integer height, Integer weight, Double fatRate){
        user.setHeight(height);
        user.setHeight(weight);
        user.setFatRate(fatRate);
        return true;
    }

    @RequestMapping("getUserInfo")
    @ResponseBody
    public Map<String, String> getUserInfo(String userName){
        Map<String, String> res = new HashMap<>();
        res.put("height", user.getHeight() + "");
        res.put("weight", user.getWeight() + "");
        res.put("fatRate", user.getFatRate() + "");
        return res;
    }

}
