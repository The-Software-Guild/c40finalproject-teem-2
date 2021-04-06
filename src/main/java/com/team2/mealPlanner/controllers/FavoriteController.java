package com.team2.mealPlanner.controllers;

import com.team2.mealPlanner.daos.CustomMealDao;
import com.team2.mealPlanner.daos.PlanDao;
import com.team2.mealPlanner.daos.UserDao;
import com.team2.mealPlanner.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FavoriteController {

    int userId = 1;

    @Autowired
    UserDao userDao;

    @Autowired
    CustomMealDao customMealDao;

    @Autowired
    PlanDao planDao;


    @GetMapping("/favorites")
    public String displayFavorites(Model model) {
        List<Integer> favorites = userDao.getFavoritesById(userId);
        model.addAttribute("favorites", favorites);
        return "favorites";
    }

    @PostMapping("/addFavorite")
    public String addFavorite(Integer mealId){
       userDao.addFavorite(mealId, userId);
        return "redirect:/favorites";
    }

    @GetMapping("/deleteFavorite")
    public String deleteFavorite(Integer mealId){
    userDao.deleteFavorite(mealId, userId);
        return "redirect:/favorites";
    }

}
