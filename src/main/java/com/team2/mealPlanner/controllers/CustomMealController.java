package com.team2.mealPlanner.controllers;

import com.team2.mealPlanner.daos.CustomMealDao;
import com.team2.mealPlanner.daos.UserDao;
import com.team2.mealPlanner.entities.CustomMeal;
import com.team2.mealPlanner.entities.User;
import com.team2.mealPlanner.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomMealController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    UserDao userDao;

    @Autowired
    CustomMealDao customMealDao;

    @GetMapping("/customMeals")
    public String displayCustomMeals(Model model) {
        User user = userServiceImp.findUserByUsername();
        List<CustomMeal> customMeals = userDao.getCustomMealsById(user.getId());
        model.addAttribute("customMeals", customMeals);
        return "customMeal/customMeals";
    }

    @PostMapping("/addCustomMeal")
    public String addCustomMeal(CustomMeal customMeal) {
        User user = userServiceImp.findUserByUsername();
        customMeal.setUserId(user.getId());
        customMealDao.add(customMeal);
        return "redirect:/customMeals";
    }

    @GetMapping("/customMealDetail")
    public String customMealDetail(Integer id, Model model) {
        CustomMeal customMeal = customMealDao.getCustomMealById(id);
        model.addAttribute("customMeal", customMeal);
        return "customMeal/customMealDetail";
    }

    @GetMapping("/deleteCustomMeal")
    public String deleteCustomMeal(Integer id, Model model) {
        CustomMeal customMeal = customMealDao.getCustomMealById(id);
        model.addAttribute("customMeal", customMeal);
        return "customMeal/deleteCustomMeal";
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
        return "customMeal/editCustomMeal";
    }

    @PostMapping("/editCustomMeal")
    public String performEditCustomMeal(CustomMeal customMeal) {
        User user = userServiceImp.findUserByUsername();
        customMeal.setUserId(user.getId());
        customMealDao.update(customMeal);
        return "redirect:/customMeals";
    }
}




