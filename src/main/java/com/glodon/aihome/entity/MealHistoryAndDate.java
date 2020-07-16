package com.glodon.aihome.entity;

import java.util.List;
import java.util.Map;

public class MealHistoryAndDate {

    private int date;
    // [{"mealtime":0, "meal":[{"name":"苹果", "weight":0.5}]}]
    private List<Map<String, Object>> mealHistoryOfDay;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public List<Map<String, Object>> getMealHistoryOfDay() {
        return mealHistoryOfDay;
    }

    public void setMealHistoryOfDay(List<Map<String, Object>> mealHistoryOfDay) {
        this.mealHistoryOfDay = mealHistoryOfDay;
    }
}
