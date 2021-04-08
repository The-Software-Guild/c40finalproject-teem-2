package com.team2.mealPlanner.controllers;

import com.team2.mealPlanner.daos.CustomMealDao;
import com.team2.mealPlanner.daos.UserDao;
import com.team2.mealPlanner.entities.CustomMeal;
import com.team2.mealPlanner.entities.User;
import com.team2.mealPlanner.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CustomMealController {
<<<<<<< Updated upstream

    @Autowired
    UserServiceImp userServiceImp;
=======
    int userId = 1;
>>>>>>> Stashed changes

    @Autowired
    UserDao userDao;

    @Autowired
    CustomMealDao customMealDao;

    Set<ConstraintViolation<CustomMeal>> violations= new HashSet<>();

    @GetMapping("/customMeals")
    public String displayCustomMeals(Model model) {
        User user = userServiceImp.findUserByUsername();
        List<CustomMeal> customMeals = userDao.getCustomMealsById(user.getId());
        model.addAttribute("customMeals", customMeals);
        model.addAttribute("errors", violations);
        return "customMeal/customMeals";
    }

    @PostMapping("/addCustomMeal")
    public String addCustomMeal(CustomMeal customMeal) {
<<<<<<< Updated upstream
        User user = userServiceImp.findUserByUsername();
        customMeal.setUserId(user.getId());
        customMealDao.add(customMeal);
=======
        customMeal.setUserId(userId);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(customMeal);
        if(violations.isEmpty()) {
            customMealDao.add(customMeal);
        }
>>>>>>> Stashed changes
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
        model.addAttribute("errors", violations);
        return "customMeal/editCustomMeal";
    }

    @PostMapping("/editCustomMeal")
    public String performEditCustomMeal(CustomMeal customMeal) {
<<<<<<< Updated upstream
        User user = userServiceImp.findUserByUsername();
        customMeal.setUserId(user.getId());
        customMealDao.update(customMeal);
=======
        customMeal.setUserId(userId);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(customMeal);

        if(violations.isEmpty()) {
            customMealDao.update(customMeal);
        }
>>>>>>> Stashed changes
        return "redirect:/customMeals";
    }
}




