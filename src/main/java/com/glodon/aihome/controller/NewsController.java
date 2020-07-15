package com.glodon.aihome.controller;

import com.glodon.aihome.entity.HealthNews;
import com.glodon.aihome.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private User user;

    @RequestMapping("getAllNews")
    @ResponseBody
    public List<HealthNews> getAllNews(){
        return user.getAll();
    }

    @RequestMapping("getFavorites")
    @ResponseBody
    public List<HealthNews> getFavorites(){
        return user.getFavorites();
    }

    @RequestMapping("favorite")
    @ResponseBody
    public String favorite(String id){
        boolean success = false;
        for (HealthNews healthNews : user.getAll()) {
            if(healthNews.getId() == Integer.parseInt(id)){
                success = true;
                user.getFavorites().add(healthNews);
            }
        }
        return String.valueOf(success);
    }

}
