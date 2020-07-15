package com.glodon.aihome.baiduapiinvoke;

import java.net.URLEncoder;

/**
 * 细粒度图像识别
 */
public class BaiduAPIService {

    public static String ingredient(String file) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/classify/ingredient";
        try {
            // 本地文件路径
            String filePath = file;
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            return ingredient(imgData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ingredient(byte[] imgData) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/classify/ingredient";
        try {
            // 本地文件路径
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String dir = "/home/ych/IdeaProjects/hadoop/src/main/resources/";
        System.out.println(BaiduAPIService.ingredient(dir + "orange_strawberry.png"));
        System.out.println(BaiduAPIService.ingredient(dir + "orange.png"));
        System.out.println(BaiduAPIService.ingredient(dir + "strawberry.png"));
    }
}