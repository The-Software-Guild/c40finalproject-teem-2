package com.team2.mealPlanner.controllers;

import com.team2.mealPlanner.daos.*;
import com.team2.mealPlanner.entities.*;
import com.team2.mealPlanner.services.MealService;
import com.team2.mealPlanner.services.UserServiceImp;
import com.team2.mealPlanner.utils.ApiMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlanController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    UserDao userDao;

    @Autowired
    PlanDao planDao;

    @Autowired
    MealTypeDao mealTypeDao;

    @Autowired
    PlanMealDao planMealDao;

    @Autowired
    CustomMealDao customMealDao;

    @Autowired
    MealService mealService;

    @GetMapping("/plans")
    public String displayPlans(Model model) {

        User user = userServiceImp.findUserByUsername();

        List<Plan> plans = userDao.getAllPlansById(user.getId());
        List<CustomMeal> customMeals = userDao.getCustomMealsById(user.getId());
        List<ApiMeal> meals = mealService.getMealsFromApi();

        model.addAttribute("plans", plans);
        model.addAttribute("customMeals", customMeals);
        model.addAttribute("meals", meals);

        return "plan/plans";
    }

    @PostMapping("/addPlan")
    public String addPlan(Plan plan, HttpServletRequest request) {

        User user = userServiceImp.findUserByUsername();

        plan.setIdUser(user.getId());
        plan = planDao.addPlan(plan);

        List<PlanMeal> planMeals = new ArrayList<>();

        String[] breakfastMealsIds = request.getParameterValues("breakfastMealsIds");
        if (breakfastMealsIds != null) {
            addPlanMeals(plan, breakfastMealsIds, 1, false, planMeals);
        }

        String[] customBreakfastMealsIds = request.getParameterValues("customBreakfastMealsIds");
        if (customBreakfastMealsIds != null) {
            addPlanMeals(plan, customBreakfastMealsIds, 1, true, planMeals);
        }

        String[] lunchMealsIds = request.getParameterValues("lunchMealsIds");
        if (lunchMealsIds != null) {
            addPlanMeals(plan, lunchMealsIds, 2, false, planMeals);
        }

        String[] customLunchMealsIds = request.getParameterValues("customLunchMealsIds");
        if (customLunchMealsIds != null) {
            addPlanMeals(plan, customLunchMealsIds, 2, true, planMeals);
        }

        String[] dinnerMealsIds = request.getParameterValues("dinnerMealsIds");
        if (dinnerMealsIds != null) {
            addPlanMeals(plan, dinnerMealsIds, 3, false, planMeals);
        }

        String[] customDinnerMealsIds = request.getParameterValues("customDinnerMealsIds");
        if (customDinnerMealsIds != null) {
            addPlanMeals(plan, customDinnerMealsIds, 3, true, planMeals);
        }

        return "redirect:/plans";
    }

    private void addPlanMeals(Plan plan, String[] mealIds, int mealTypeId, boolean isCustom,
                              List<PlanMeal> planMeals) {
        for (String mealId : mealIds) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setMealId(Integer.parseInt(mealId));
            planMeal.setPlanId(plan.getId());
            planMeal.setMealTypeId(mealTypeId);
            planMeal.setCustom(isCustom);
            planMeal.setMealType(mealTypeDao.getMealTypeById(mealTypeId));
            planMealDao.addPlanMeal(planMeal);
            planMeals.add(planMeal);
        }
    }

    @GetMapping("/planDetail")
    public String planDetail(Integer id, Model model) {

        Plan plan = planDao.getPlanById(id);
        List<PlanMeal> planMeals = planMealDao.getAllPlanMealsByPlanId(id);

        /*
        class Meal {
            int id;
            String name;
            String mealTypeName;
            boolean isCustom;
        }*/

        List<Meal> customPlanMeals = new ArrayList<>();
        List<Meal> defaultPlanMeals = new ArrayList<>();

        for (PlanMeal planMeal: planMeals) {
            if (planMeal.isCustom()) {
                Meal meal = new Meal();
                meal.setId(planMeal.getId());
                meal.setName(customMealDao.getCustomMealById(planMeal.getMealId()).getName());
                meal.setMealTypeName(planMeal.getMealType().getName());
                meal.setCustom(true);
                meal.setMealId(planMeal.getMealId());
                customPlanMeals.add(meal);

                /*
                meal.id = planMeal.getId();
                meal.name = customMealDao.getCustomMealById(planMeal.getMealId()).getName();
                meal.mealTypeName = planMeal.getMealType().getName();
                meal.isCustom = true;
                customPlanMeals.add(meal);*/
            } else {
                Meal meal = new Meal();
                ApiMeal apiMeal = mealService.getMealById(planMeal.getMealId()).get();
                meal.setId(planMeal.getId());
                meal.setName(apiMeal.getStrMeal());
                meal.setMealTypeName(planMeal.getMealType().getName());
                meal.setCustom(false);
                meal.setMealId(planMeal.getMealId());
                defaultPlanMeals.add(meal);
            }
        }

        model.addAttribute("plan", plan);
        model.addAttribute("planMeals", customPlanMeals);
        model.addAttribute("defaultPlanMeals", defaultPlanMeals);

        return "plan/planDetail";
    }

    @GetMapping("/deletePlan")
    public String deletePlan(Integer id, Model model) {

        Plan plan = planDao.getPlanById(id);

        List<PlanMeal> planMeals = planMealDao.getAllPlanMealsByPlanId(id);

        List<Meal> customPlanMeals = new ArrayList<>();
        List<Meal> defaultPlanMeals = new ArrayList<>();

        for (PlanMeal planMeal: planMeals) {
            if (planMeal.isCustom()) {
                Meal meal = new Meal();
                meal.setId(planMeal.getId());
                meal.setName(customMealDao.getCustomMealById(planMeal.getMealId()).getName());
                meal.setMealTypeName(planMeal.getMealType().getName());
                meal.setCustom(true);
                meal.setMealId(planMeal.getMealId());
                customPlanMeals.add(meal);
            } else {
                Meal meal = new Meal();
                ApiMeal apiMeal = mealService.getMealById(planMeal.getMealId()).get();
                meal.setId(planMeal.getId());
                meal.setName(apiMeal.getStrMeal());
                meal.setMealTypeName(planMeal.getMealType().getName());
                meal.setCustom(false);
                meal.setMealId(planMeal.getMealId());
                defaultPlanMeals.add(meal);
            }
        }

        model.addAttribute("plan", plan);
        model.addAttribute("planMeals", customPlanMeals);
        model.addAttribute("defaultPlanMeals", defaultPlanMeals);

        return "plan/deletePlan";
    }

    @GetMapping("/performDeletePlan")
    public String performDeletePlan(Integer id) {
        planDao.deletePlan(id);
        return "redirect:/plans";
    }

    @GetMapping("/deletePlanMeal")
    public String deletePlanMeal(Integer id) {
        int planId = planMealDao.getPlanMealById(id).getPlanId();
        planMealDao.deletePlanMeal(id);
        return "redirect:/planDetail?id=" + planId;
    }

    @GetMapping("/editPlan")
    public String editPlan(Integer id, Model model) {

        User user = userServiceImp.findUserByUsername();

        Plan plan = planDao.getPlanById(id);
        List<PlanMeal> planMeals = planMealDao.getAllPlanMealsByPlanId(plan.getId());

        List<CustomMeal> customMeals = userDao.getCustomMealsById(user.getId());
        List<CustomMeal> customBreakfastMeals = new ArrayList<>();
        List<CustomMeal> customLunchMeals = new ArrayList<>();
        List<CustomMeal> customDinnerMeals = new ArrayList<>();

        List<ApiMeal> meals = mealService.getMealsFromApi();
        List<ApiMeal> breakfastMeals = new ArrayList<>();
        List<ApiMeal> lunchMeals = new ArrayList<>();
        List<ApiMeal> dinnerMeals = new ArrayList<>();

        if (planMeals != null) {
            for (PlanMeal planMeal : planMeals) {
                if (planMeal.isCustom()) {
                    if (planMeal.getMealTypeId() == 1) {
                        customBreakfastMeals.add(customMealDao.getCustomMealById(planMeal.getMealId()));
                    } else if (planMeal.getMealTypeId() == 2) {
                        customLunchMeals.add(customMealDao.getCustomMealById(planMeal.getMealId()));
                    } else if (planMeal.getMealTypeId() == 3) {
                        customDinnerMeals.add(customMealDao.getCustomMealById(planMeal.getMealId()));
                    }
                } else {
                    if (planMeal.getMealTypeId() == 1) {
                        //breakfastMeals.add();
                    } else if (planMeal.getMealTypeId() == 2) {
                        //lunchMeals.add();
                    } else if (planMeal.getMealTypeId() == 3) {
                        //dinnerMeals.add();
                    }
                }
            }
        }

        model.addAttribute("plan", plan);
        model.addAttribute("customMeals", customMeals);
        model.addAttribute("customBreakfastMeals", customBreakfastMeals);
        model.addAttribute("customLunchMeals", customLunchMeals);
        model.addAttribute("customDinnerMeals", customDinnerMeals);
        model.addAttribute("meals", meals);
        model.addAttribute("breakfastMeals", breakfastMeals);
        model.addAttribute("lunchMeals", lunchMeals);
        model.addAttribute("dinnerMeals", dinnerMeals);

        return "plan/editPlan";
    }

    @PostMapping("/editPlan")
    @CrossOrigin
    public String performEditPlan(Plan plan, HttpServletRequest request) {

        User user = userServiceImp.findUserByUsername();

        plan.setIdUser(user.getId());
        planDao.updatePlan(plan);

        List<PlanMeal> planMeals = new ArrayList<>();

        String[] breakfastMealsIds = request.getParameterValues("breakfastMealsIds");
        if (breakfastMealsIds != null) {
            addPlanMeals(plan, breakfastMealsIds, 1, false, planMeals);
        }

        String[] customBreakfastMealsIds = request.getParameterValues("customBreakfastMealsIds");
        if (customBreakfastMealsIds != null) {
            addPlanMeals(plan, customBreakfastMealsIds, 1, true, planMeals);
        }

        String[] lunchMealsIds = request.getParameterValues("lunchMealsIds");
        if (lunchMealsIds != null) {
            addPlanMeals(plan, lunchMealsIds, 2, false, planMeals);
        }

        String[] customLunchMealsIds = request.getParameterValues("customLunchMealsIds");
        if (customLunchMealsIds != null) {
            addPlanMeals(plan, customLunchMealsIds, 2, true, planMeals);
        }

        String[] dinnerMealsIds = request.getParameterValues("dinnerMealsIds");
        if (dinnerMealsIds != null) {
            addPlanMeals(plan, dinnerMealsIds, 3, false, planMeals);
        }

        String[] customDinnerMealsIds = request.getParameterValues("customDinnerMealsIds");
        if (customDinnerMealsIds != null) {
            addPlanMeals(plan, customDinnerMealsIds, 3, true, planMeals);
        }

        return "redirect:/planDetail?id=" + plan.getId();
    }

    @GetMapping("/editPlanMeal")
    public String editPlanMeal(Integer id, Model model) {

        User user = userServiceImp.findUserByUsername();

        PlanMeal planMeal = planMealDao.getPlanMealById(id);

        Plan plan = planDao.getPlanById(planMeal.getPlanId());
        plan.setPlanMeals(planMealDao.getAllPlanMealsByPlanId(planMeal.getPlanId()));

        List<CustomMeal> customMeals = userDao.getCustomMealsById(user.getId());
        List<ApiMeal> meals = mealService.getMealsFromApi();

        model.addAttribute("planMeal", planMeal);
        model.addAttribute("plan", plan);
        model.addAttribute("customMeals", customMeals);
        model.addAttribute("meals", meals);

        return "plan/editPlanMeal";
    }

    @PostMapping("/editPlanMeal")
    public String performEditPlanMeal(Integer id, HttpServletRequest request) {

        String mealId = request.getParameter("mealId");
        String customMealId = request.getParameter("customMealId");

        PlanMeal planMeal = planMealDao.getPlanMealById(id);

        if (mealId != null && customMealId == null && !planMeal.isCustom()) {
            planMeal.setMealId(Integer.parseInt(mealId));
            planMeal.setCustom(false);
        } else if (mealId == null && customMealId != null && planMeal.isCustom()) {
            planMeal.setMealId(Integer.parseInt(customMealId));
            planMeal.setCustom(true);
        }

        planMealDao.updatePlanMeal(planMeal);

        return "redirect:/planDetail?id=" + planMeal.getPlanId();
    }
}
