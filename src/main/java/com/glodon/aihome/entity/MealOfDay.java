package com.glodon.aihome.entity;

import java.util.Date;
import java.util.Map;

public class MealOfDay {

    private Date day;
    private Map<String, Integer> nutrition;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Map<String, Integer> getNutrition() {
        return nutrition;
    }

    public void setNutrition(Map<String, Integer> nutrition) {
        this.nutrition = nutrition;
    }
}
