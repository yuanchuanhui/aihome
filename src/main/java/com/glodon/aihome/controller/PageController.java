package com.glodon.aihome.controller;

import com.glodon.aihome.entity.Variable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("recordinput")
    public String recordInput(int eatTime){
        Variable.eatTime =eatTime;
        return "recordinput.html";
    }

}
