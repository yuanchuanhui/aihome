package com.glodon.aihome.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NutritionOfDay {

    private Map<String, Float> nutrition = new HashMap<>();

    public Map<String, Float> getNutrition() {
        return nutrition;
    }

    public void setNutrition(Map<String, Float> nutrition) {
        this.nutrition = nutrition;
    }
}
