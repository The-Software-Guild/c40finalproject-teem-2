package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.Plan;

import java.util.List;


public interface PlanDao {
    Plan getPlanById(int id);
    List<Plan> getAllPlans();
    Plan addPlan(Plan plan);
    boolean updatePlan(Plan plan);
    boolean deletePlan(int id);

}
