package com.glodon.aihome.entity;

import com.glodon.aihome.controller.WeightController;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class User {

    private String userName;
    private String password;
    private Map<Integer, NutritionOfDay> meals;
    private List<HealthNews> favorites = new ArrayList<>();
    private List<HealthNews> all = new ArrayList<>();

    public User() throws IOException {
        this.meals = new HashMap<>();
        int today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        // 填充用户历史信息
        for (int i = 1; i < 5; i++) {
            WeightController.getWeight("苹果", today - i, this);
        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(NewsTest.class.getClassLoader().getResourceAsStream("news/all.txt")));
        String line = null;
        int i = 0;
        // 初始化所有文章
        while ((line = bf.readLine()) != null) {
            HealthNews healthNews = new HealthNews();
            String[] elements = line.split("\\*\\*\\*");
            healthNews.setId(i++);
            healthNews.setTitle(elements[0]);
            healthNews.setDesc(elements[1]);
            healthNews.setContent(elements[2]);
            all.add(healthNews);
        }
        favorites.add(all.get(0));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<Integer, NutritionOfDay> getMeals() {
        return meals;
    }

    public void setMeals(Map<Integer, NutritionOfDay> meals) {
        this.meals = meals;
    }

    public List<HealthNews> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<HealthNews> favorites) {
        this.favorites = favorites;
    }

    public List<HealthNews> getAll() {
        return all;
    }

    public void setAll(List<HealthNews> all) {
        this.all = all;
    }
}
