package com.glodon.aihome.baiduapiinvoke;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 细粒度图像识别
 */
public class BaiduAPIService {

    public static String ingredient(String file) {
        // 请求url
        try {
            byte[] imgData = FileUtil.readFileByBytes(file);
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

    public static void main(String[] args) throws Exception{
        URL url = new URL("https://img.bemfa.com/9ff645b1dc924fb39d69dae76fe864d5-d31d40dd26ba770caabbb8b58c03efd1-1594896579.jpg");
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        System.out.println(ingredient(bytes));
    }
}