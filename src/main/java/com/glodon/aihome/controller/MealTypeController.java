package com.glodon.aihome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class MealTypeController {

    @RequestMapping("retrieval")
    @ResponseBody
    public List<String> retrieval(String key){
        return Arrays.asList("苹果", "苹果醋", "苹果派", "苹果罐头");
    }

}
