package com.glodon.aihome.controller;

import com.glodon.aihome.entity.Meal;
import com.glodon.aihome.entity.MealHistoryAndDate;
import com.glodon.aihome.entity.NutritionOfDay;
import com.glodon.aihome.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class MealHistoryController {

    @Autowired
    private User user;

    /**
     * addUserMeal?date=2020年7月20日&eattime=1&food=苹果
     */
    @RequestMapping("addUserMeal")
    @ResponseBody
    public String addUserMeal(Date date, int eatTime, String food){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        WeightController.getWeight(food, calendar.get(Calendar.DAY_OF_YEAR), eatTime, user);
        return "success";
    }

    /**
     * http://localhost:8080/getUserMealHistory?date=2020年7月20日&eattime=1
     */
    @RequestMapping("getUserMealHistory")
    @ResponseBody
    public List<MealHistoryAndDate> getUserMealHistory(Date date, Integer eatTime){
        int dayOfYear = -1;
        if(date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        }
        List<MealHistoryAndDate> res = new ArrayList<>();
        for (Map.Entry<Integer, NutritionOfDay> entry : user.getMeals().entrySet()) {
            if(date != null && entry.getKey() != dayOfYear){
                continue;
            }
            // 遍历每一天
            MealHistoryAndDate history = new MealHistoryAndDate();
            history.setDate(entry.getKey());
            List<Map<String, Object>> list = new ArrayList<>();
            Map<Integer, Map<String, Double>> mealHistory = entry.getValue().getMealHistory();
            for (Map.Entry<Integer, Map<String, Double>> mapEntry : mealHistory.entrySet()) {
                if(eatTime != null && mapEntry.getKey() != eatTime){
                    continue;
                }
                // 遍历每一餐
                Map<String, Object> map = new HashMap<>();
                map.put("mealtime", mapEntry.getKey());
                List<Meal> meals = new ArrayList<>();
                for (Map.Entry<String, Double> stringDoubleEntry : mapEntry.getValue().entrySet()) {
                    // 遍历每一个食物
                    Meal meal = new Meal();
                    meal.setName(stringDoubleEntry.getKey());
                    meal.setWeight(stringDoubleEntry.getValue());
                    meals.add(meal);
                }
                map.put("meal", meals);
                list.add(map);
            }
            history.setMealHistoryOfDay(list);
            res.add(history);
        }
        return res;
    }

    /**
     * deleteUserMeal?date=2020年7月20日&eattime=1
     */
    @RequestMapping("deleteUserMeal")
    @ResponseBody
    public String deleteUserMeal(Date date, int eatTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        user.getMeals().get(dayOfYear).getMealHistory().remove(eatTime);
        return "success";
    }

}
