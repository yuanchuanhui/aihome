package com.glodon.aihome.entity;

import java.util.Arrays;

public class ImageRecognitionResult {

    private String log_id;
    private int result_num;
    private OneImageRecognitionResult[] result;

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public OneImageRecognitionResult[] getResult() {
        return result;
    }

    public void setResult(OneImageRecognitionResult[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ImageRecognitionResult{" +
                "log_id='" + log_id + '\'' +
                ", result_num=" + result_num +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
