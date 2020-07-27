package com.glodon.aihome;

import com.glodon.aihome.entity.ImageRecognitionResult;
import com.google.gson.Gson;

public class GsonTest {

    public static void main(String[] args) {
        String json = "{\"log_id\": 8254139881491903762, \"result_num\": 5, \"result\": [{\"score\": 0.9873487949371338, \"name\": \"非果蔬食材\"}, {\"score\": 0.0017519384855404496, \"name\": \"甜瓜\"}, {\"score\": 0.00045834577758796513, \"name\": \"蛇豆\"}, {\"score\": 0.00040516379522159696, \"name\": \"番石榴\"}, {\"score\": 0.0003389130288269371, \"name\": \"洋葱\"}]}";
        System.out.println(new Gson().fromJson(json, ImageRecognitionResult.class));
    }

}
