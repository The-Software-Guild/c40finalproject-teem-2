package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.Plan;
import com.team2.mealPlanner.entities.PlanMeal;

import java.util.List;

public interface PlanMealDao {
    PlanMeal getPlanMealById (int id);
    List<PlanMeal> getAllPlanMeals();
    PlanMeal addPlanMeal(PlanMeal planMeal);
    boolean updatePlanMeal(PlanMeal planMeal);
    boolean deletePlanMeal(int id);


}
