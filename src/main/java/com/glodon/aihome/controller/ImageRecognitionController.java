package com.glodon.aihome.controller;

import com.glodon.aihome.baiduapiinvoke.BaiduAPIService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ImageRecognitionController {

    @RequestMapping(value = "imageRecognition", method = RequestMethod.POST)
    @ResponseBody
    public String imageRecognition(@RequestParam(value = "file")MultipartFile file) throws IOException {
        System.out.println(file.getSize());
        return BaiduAPIService.ingredient(file.getBytes());
    }

}
