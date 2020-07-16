package com.glodon.aihome.controller;

import com.glodon.aihome.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class WeightController {

    @Autowired
    private User user;

    /**
     * 返回一个随机的weight值，并更新该用户的饮食状态
     */
    @RequestMapping("getWeight")
    @ResponseBody
    public String getWeight(String mealType){
        return getWeight(mealType, Calendar.getInstance().get(Calendar.DAY_OF_YEAR), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), user);
    }

    public static String getWeight(String mealType, int day, int hour, User user){
        // 随机生成重量，0-0.5kg
        float weight = (float) (Math.random() * 0.5);
        // 根据日期取出今天的营养对象
        NutritionOfDay nutritionOfDay = user.getMeals().getOrDefault(day, new NutritionOfDay());
        // 根据食物种类得到其营养组成
        Map<String, Float> nutrition = meal2Nutrition(mealType);
        // 更新今天的摄入量
        for (Map.Entry<String, Float> entry : nutrition.entrySet()) {
            nutritionOfDay.getNutrition().put(entry.getKey(),
                    nutritionOfDay.getNutrition().getOrDefault(entry.getKey(), 0f) + (entry.getValue() * weight));
        }
        // 添加食品历史
        Map<Integer, Map<String, Double>> mealHistory = nutritionOfDay.getMealHistory();
        int flag = 0;
        if(hour < 11 && hour >= 5){
            flag = MealTime.BREAKFAST;
        }else if(hour < 16 && hour >= 11){
            flag = MealTime.LUNCH;
        }else if(hour < 22 && hour >= 16){
            flag = MealTime.DINNER;
        }
        if(mealHistory.containsKey(flag)){
            mealHistory.get(flag).put(mealType, mealHistory.get(flag).getOrDefault(mealType, 0d) + weight);
        }else {
            Map<String, Double> tmp = new HashMap<>();
            tmp.put(mealType, (double) weight);
            mealHistory.put(flag, tmp);
        }
        // 更新所有日期的map
        user.getMeals().put(day, nutritionOfDay);
        return String.valueOf(weight);
    }

    /**
     * 食物种类转化为营养百分比
     */
    private static Map<String, Float> meal2Nutrition(String meal){
        Map<String, Float> map = new HashMap<>();
        map.put(Nutrition.PROTEIN, (float) (Math.random() * 0.3));
        map.put(Nutrition.SUGAR, (float) (Math.random() * 0.3));
        map.put(Nutrition.FAT, (float) (Math.random() * 0.3));
        return map;
    }

    /**
     * [日期-[餐次-种类-重量]]
     */
    @RequestMapping("getUserMealHistory")
    @ResponseBody
    public List<MealHistoryAndDate> getUserMealHistory(){
        List<MealHistoryAndDate> res = new ArrayList<>();
        for (Map.Entry<Integer, NutritionOfDay> entry : user.getMeals().entrySet()) {
            MealHistoryAndDate history = new MealHistoryAndDate();
            history.setDate(entry.getKey());
            List<Map<String, Object>> list = new ArrayList<>();
            Map<Integer, Map<String, Double>> mealHistory = entry.getValue().getMealHistory();
            for (Map.Entry<Integer, Map<String, Double>> mapEntry : mealHistory.entrySet()) {
                Map<String, Object> map = new HashMap<>();
                map.put("mealtime", mapEntry.getKey());
                List<Meal> meals = new ArrayList<>();
                for (Map.Entry<String, Double> stringDoubleEntry : mapEntry.getValue().entrySet()) {
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

}
