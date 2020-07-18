package com.glodon.aihome.controller;

import com.glodon.aihome.baiduapiinvoke.BaiduAPIService;
import com.glodon.aihome.entity.ImageRecognitionResult;
import com.glodon.aihome.entity.OneImageRecognitionResult;
import com.glodon.aihome.entity.User;
import com.glodon.aihome.entity.Variable;
import com.google.gson.Gson;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Calendar;

@Controller
public class ImageRecognitionController {

    @Autowired
    private User user;

    @RequestMapping(value = "imageRecognition", method = RequestMethod.POST)
    @ResponseBody
    public String imageRecognition(@RequestParam(value = "file")MultipartFile file) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(new ByteArrayInputStream(file.getBytes())).scale(1f).outputQuality(0.1f).toOutputStream(outputStream);
        String json = BaiduAPIService.ingredient(outputStream.toByteArray());
        Gson gson = new Gson();
        ImageRecognitionResult result = gson.fromJson(json, ImageRecognitionResult.class);
        for (OneImageRecognitionResult oneImageRecognitionResult : result.getResult()) {
            if(!oneImageRecognitionResult.getName().equals("非果蔬食材")){
                WeightController.getWeight(
                        oneImageRecognitionResult.getName(),
                        Calendar.getInstance().get(Calendar.DAY_OF_YEAR),
                        Variable.eatTime,
                        user
                );
                return oneImageRecognitionResult.getName();
            }
        }
        return null;
    }

}
