package com.glodon.aihome.entity;

import java.util.List;

public class User {

    private String userName;
    private List<MealOfDay> meals;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<MealOfDay> getMeals() {
        return meals;
    }

    public void setMeals(List<MealOfDay> meals) {
        this.meals = meals;
    }
}
