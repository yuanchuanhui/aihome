package com.glodon.aihome.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NutritionOfDay {

    // 营养-重量
    private Map<String, Float> nutrition = new HashMap<>();
    // [0,3]餐次-食物种类-重量
    // 暂定22-5为0，表示其他餐，5-11为1表示早餐，11-16表示午餐，16-22表示晚餐
    private Map<Integer, Map<String, Double>> mealHistory = new HashMap<>();

    public Map<String, Float> getNutrition() {
        return nutrition;
    }

    public void setNutrition(Map<String, Float> nutrition) {
        this.nutrition = nutrition;
    }

    public Map<Integer, Map<String, Double>> getMealHistory() {
        return mealHistory;
    }

    public void setMealHistory(Map<Integer, Map<String, Double>> mealHistory) {
        this.mealHistory = mealHistory;
    }
}
