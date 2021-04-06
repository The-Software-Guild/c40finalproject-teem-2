package com.team2.mealPlanner.controllers;

import com.team2.mealPlanner.daos.MealTypeDao;
import com.team2.mealPlanner.daos.PlanDao;
import com.team2.mealPlanner.daos.PlanMealDao;
import com.team2.mealPlanner.daos.UserDao;
import com.team2.mealPlanner.entities.Plan;
import com.team2.mealPlanner.entities.PlanMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlanController {

    int userId = 1;

    @Autowired
    UserDao userDao;

    @Autowired
    PlanDao planDao;

    @Autowired
    MealTypeDao mealTypeDao;

    @Autowired
    PlanMealDao planMealDao;

    @GetMapping("/plans")
    public String displayPlans(Model model) {
        List<Plan> plans = userDao.getAllPlansById(userId);
        model.addAttribute("plans", plans);
        return "plans";
    }

    @PostMapping("/addPlan")
    public String addPlan(Plan plan, HttpServletRequest request) {

        plan.setIdUser(userId);
        plan = planDao.addPlan(plan);

        List<PlanMeal> planMeals = new ArrayList<>();

        String[] breakfastMealsIds = request.getParameterValues("breakfastMealsIds");
        for (String breakfastMealsId : breakfastMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(breakfastMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(1);
            planMeal.setCustom(false);
            planMeal.setMealType(mealTypeDao.getMealTypeById(1));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] customBreakfastMealsIds = request.getParameterValues("customBreakfastMealsIds");
        for (String customBreakfastMealsId : customBreakfastMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(customBreakfastMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(1);
            planMeal.setCustom(true);
            planMeal.setMealType(mealTypeDao.getMealTypeById(1));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] lunchMealsIds = request.getParameterValues("lunchMealsIds");
        for (String lunchMealsId : lunchMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(lunchMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(2);
            planMeal.setCustom(false);
            planMeal.setMealType(mealTypeDao.getMealTypeById(2));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] customLunchMealsIds = request.getParameterValues("customLunchMealsIds");
        for (String customLunchMealsId : customLunchMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(customLunchMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(2);
            planMeal.setCustom(true);
            planMeal.setMealType(mealTypeDao.getMealTypeById(2));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] dinnerMealsIds = request.getParameterValues("dinnerMealsIds");
        for (String dinnerMealsId : dinnerMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(dinnerMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(3);
            planMeal.setCustom(false);
            planMeal.setMealType(mealTypeDao.getMealTypeById(3));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] customDinnerMealsIds = request.getParameterValues("customDinnerMealsIds");
        for (String customDinnerMealsId : customDinnerMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(customDinnerMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(3);
            planMeal.setCustom(true);
            planMeal.setMealType(mealTypeDao.getMealTypeById(3));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        plan.setPlanMeals(planMeals);
        planDao.updatePlan(plan);

        return "redirect:/plans";
    }

    @GetMapping("/planDetail")
    public String planDetail(Integer id, Model model) {
        Plan plan = planDao.getPlanById(id);
        model.addAttribute("plan", plan);
        return "planDetail";
    }

    @GetMapping("/deletePlan")
    public String deletePlan(Integer id, Model model) {
        Plan plan = planDao.getPlanById(id);
        model.addAttribute("plan", plan);
        return "deletePlan";
    }

    @GetMapping("/performDeletePlan")
    public String performDeletePlan(Integer id) {
        planDao.deletePlan(id);
        return "redirect:/plans";
    }

    @GetMapping("/editPlan")
    public String editPlan(Integer id, Model model) {
        Plan plan = planDao.getPlanById(id);
        model.addAttribute("plan", plan);
        return "editPlan";
    }

    @PostMapping("/editPlan")
    public String performEditPlan(Plan plan, HttpServletRequest request) {

        plan.setIdUser(userId);

        List<PlanMeal> planMeals = new ArrayList<>();

        String[] breakfastMealsIds = request.getParameterValues("breakfastMealsIds");
        for (String breakfastMealsId : breakfastMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(breakfastMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(1);
            planMeal.setCustom(false);
            planMeal.setMealType(mealTypeDao.getMealTypeById(1));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] customBreakfastMealsIds = request.getParameterValues("customBreakfastMealsIds");
        for (String customBreakfastMealsId : customBreakfastMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(customBreakfastMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(1);
            planMeal.setCustom(true);
            planMeal.setMealType(mealTypeDao.getMealTypeById(1));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] lunchMealsIds = request.getParameterValues("lunchMealsIds");
        for (String lunchMealsId : lunchMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(lunchMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(2);
            planMeal.setCustom(false);
            planMeal.setMealType(mealTypeDao.getMealTypeById(2));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] customLunchMealsIds = request.getParameterValues("customLunchMealsIds");
        for (String customLunchMealsId : customLunchMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(customLunchMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(2);
            planMeal.setCustom(true);
            planMeal.setMealType(mealTypeDao.getMealTypeById(2));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] dinnerMealsIds = request.getParameterValues("dinnerMealsIds");
        for (String dinnerMealsId : dinnerMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(dinnerMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(3);
            planMeal.setCustom(false);
            planMeal.setMealType(mealTypeDao.getMealTypeById(3));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        String[] customDinnerMealsIds = request.getParameterValues("customDinnerMealsIds");
        for (String customDinnerMealsId : customDinnerMealsIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(customDinnerMealsId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(3);
            planMeal.setCustom(true);
            planMeal.setMealType(mealTypeDao.getMealTypeById(3));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }

        plan.setPlanMeals(planMeals);
        planDao.updatePlan(plan);

        return "redirect:/planDetail?id=" + plan.getId();
    }
}
