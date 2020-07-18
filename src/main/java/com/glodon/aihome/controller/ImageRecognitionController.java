package com.glodon.aihome.controller;

import com.glodon.aihome.baiduapiinvoke.BaiduAPIService;
import com.glodon.aihome.entity.ImageRecognitionResult;
import com.google.gson.Gson;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class ImageRecognitionController {

    @RequestMapping(value = "imageRecognition", method = RequestMethod.POST)
    @ResponseBody
    public String imageRecognition(@RequestParam(value = "file")MultipartFile file) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(new ByteArrayInputStream(file.getBytes())).scale(1f).outputQuality(0.1f).toOutputStream(outputStream);
//        String json = ;
        return BaiduAPIService.ingredient(outputStream.toByteArray());
    }

}
