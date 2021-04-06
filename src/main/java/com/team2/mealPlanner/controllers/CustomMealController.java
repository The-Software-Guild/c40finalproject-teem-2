package com.team2.mealPlanner.controllers;

import com.team2.mealPlanner.daos.CustomMealDao;
import com.team2.mealPlanner.daos.UserDao;
import com.team2.mealPlanner.entities.CustomMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomMealController {

    int userId = 1;

    @Autowired
    UserDao userDao;

    @Autowired
    CustomMealDao customMealDao;

    @GetMapping("/customMeals")
    public String displayCustomMeals(Model model) {
        List<CustomMeal> customMeals = userDao.getCustomMealsById(userId);
        model.addAttribute("customMeals", customMeals);
        return "customMeal/customMeals";
    }

    @PostMapping("/addCustomMeal")
    public String addCustomMeal(CustomMeal customMeal) {
        customMealDao.add(customMeal);
        return "redirect:/customMeals";
    }

    @GetMapping("/customMealDetail")
    public String customMealDetail(Integer id, Model model) {
        CustomMeal customMeal = customMealDao.getCustomMealById(id);
        model.addAttribute("customMeal", customMeal);
        return "customMealDetail";
    }

    @GetMapping("/deleteCustomMeal")
    public String deleteCustomMeal(Integer id, Model model) {
        CustomMeal customMeal = customMealDao.getCustomMealById(id);
        model.addAttribute("customMeal", customMeal);
        return "performDeleteCustomMeal";
    }

    @GetMapping("/performDeleteCustomMeal")
    public String performDeleteCustomMeal(Integer id) {
        customMealDao.delete(id);
        return "redirect:/customMeals";
    }

    @GetMapping("/editCustomMeal")
    public String editCustomMeal(Integer id, Model model) {
        CustomMeal customMeal = customMealDao.getCustomMealById(id);
        model.addAttribute("customMeal", customMeal);
        return "editCustomMeal";
    }

    @PostMapping("/editCustomMeal")
    public String performEditCustomMeal(CustomMeal customMeal) {
        customMealDao.update(customMeal);
        return "redirect:/customMealDetail?id=" + customMeal.getId();
    }
}
