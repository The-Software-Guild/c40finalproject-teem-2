package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.MealType;

import java.util.List;

public interface MealTypeDao {

    MealType getMealTypeById(int id);
    List<MealType> getAllMealTypes();
    MealType addMealType(MealType mealType);
    boolean updateMealType(MealType mealType);
    boolean deleteMealType(int id);
}
