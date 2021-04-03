package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.CustomMeal;

import java.util.List;

public interface CustomMealDao {
    CustomMeal getCustomMealById(int id);
    List<CustomMeal> getAllCustomMeals();
    CustomMeal add(CustomMeal customMeal);
    boolean update(CustomMeal customMeal);
    boolean delete(int id);

}
