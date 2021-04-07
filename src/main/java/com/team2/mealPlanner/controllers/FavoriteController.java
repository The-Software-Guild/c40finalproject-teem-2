package com.team2.mealPlanner.controllers;

import com.team2.mealPlanner.daos.CustomMealDao;
import com.team2.mealPlanner.daos.PlanDao;
import com.team2.mealPlanner.daos.UserDao;
import com.team2.mealPlanner.entities.User;
import com.team2.mealPlanner.services.MealService;
import com.team2.mealPlanner.utils.ApiMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    MealService mealService;


    @GetMapping("/favorites")
    public String displayFavorites(Model model) {
        List<Integer> favorites = userDao.getFavoritesById(userId);
        //model.addAttribute("meals", mealService.getMealsById());
        ApiMeal testMeal = new ApiMeal();
        testMeal.setIdMeal("1");
        testMeal.setStrMeal("Test name");
        List<ApiMeal> testMeals = new ArrayList<>();
        testMeals.add(testMeal);
        model.addAttribute("meals", testMeals);
        return "meal/favorites";
    }

    @GetMapping("/mealDetail")
    public String displayMeal(Model model){
        List<Integer> userFavorites = userDao.getFavoritesById(userId);
        if(userFavorites == null){
            userFavorites = new ArrayList<>();
        }

        ApiMeal meal = new ApiMeal();
        meal.setIdMeal("1");
        meal.setStrMeal("Test name");
        meal.setStrMealThumb("https://www.themealdb.com/images/media/meals/58oia61564916529.jpg");
        meal.setStrIngredient1("Ing one");
        meal.setStrIngredient2("INg two");
        meal.setStrIngredient3("INg two");
        meal.setStrIngredient4("INg two");
        meal.setStrIngredient5("INg two");
        meal.setStrIngredient6("INg two");
        meal.setStrIngredient7("INg two");
        meal.setStrIngredient8("INg two");
        meal.setStrIngredient9("INg two");
        meal.setStrIngredient10("INg two");
        meal.setStrIngredient11("INg two");
        meal.setStrIngredient12("INg two");
        meal.setStrIngredient13("INg two");
        meal.setStrIngredient14("INg two");
        meal.setStrIngredient15("INg two");
        meal.setStrIngredient16("INg two");
        meal.setStrIngredient17("INg two");
        meal.setStrIngredient18("INg two");
        meal.setStrIngredient19("INg two");
        meal.setStrIngredient20("INg two");

        meal.setStrMeasure1("measure 2");
        meal.setStrMeasure2("measure 2");
        meal.setStrMeasure3("measure 2");
        meal.setStrMeasure4("measure 2");
        meal.setStrMeasure5("measure 2");
        meal.setStrMeasure6("measure 2");
        meal.setStrMeasure7("measure 2");
        meal.setStrMeasure8("measure 2");
        meal.setStrMeasure9("measure 2");
        meal.setStrMeasure10("measure 2");
        meal.setStrMeasure11("measure 2");
        meal.setStrMeasure12("measure 2");
        meal.setStrMeasure13("measure 2");
        meal.setStrMeasure14("measure 2");
        meal.setStrMeasure15("measure 2");
        meal.setStrMeasure16("measure 2");
        meal.setStrMeasure17("measure 2");
        meal.setStrMeasure18("measure 2");
        meal.setStrMeasure19("measure 2");
        meal.setStrMeasure20(null);
        meal.setStrYoutube("https:\\/\\/www.youtube.com\\/watch?v=1IszT_guI08");

        if(meal.getStrYoutube() != null){
            String videoId = meal.getStrYoutube().split("\\?v=")[1];
            meal.setStrYoutube(videoId);
            System.out.println(meal.getStrYoutube());
        }


        List<String> ingredientList = Arrays.asList(
                meal.getStrIngredient1(),
                meal.getStrIngredient2(),
                meal.getStrIngredient3(),
                meal.getStrIngredient4(),
                meal.getStrIngredient5(),
                meal.getStrIngredient6(),
                meal.getStrIngredient7(),
                meal.getStrIngredient8(),
                meal.getStrIngredient9(),
                meal.getStrIngredient10(),
                meal.getStrIngredient11(),
                meal.getStrIngredient12(),
                meal.getStrIngredient13(),
                meal.getStrIngredient14(),
                meal.getStrIngredient15(),
                meal.getStrIngredient16(),
                meal.getStrIngredient17(),
                meal.getStrIngredient18(),
                meal.getStrIngredient19(),
                meal.getStrIngredient20()
        );

        List<String> measurementsList = Arrays.asList(
                meal.getStrMeasure1(),
                meal.getStrMeasure2(),
                meal.getStrMeasure3(),
                meal.getStrMeasure4(),
                meal.getStrMeasure5(),
                meal.getStrMeasure6(),
                meal.getStrMeasure7(),
                meal.getStrMeasure8(),
                meal.getStrMeasure9(),
                meal.getStrMeasure10(),
                meal.getStrMeasure11(),
                meal.getStrMeasure12(),
                meal.getStrMeasure13(),
                meal.getStrMeasure14(),
                meal.getStrMeasure15(),
                meal.getStrMeasure16(),
                meal.getStrMeasure17(),
                meal.getStrMeasure18(),
                meal.getStrMeasure19(),
                meal.getStrMeasure20()
        );

        Boolean isFavorite = userFavorites.contains(Integer.parseInt(meal.getIdMeal()));

        model.addAttribute("meal", meal);
        model.addAttribute("ingredients", ingredientList);
        model.addAttribute("measurements", measurementsList);
        model.addAttribute("isFavorite", isFavorite);

        return "meal/mealDetail";
    }

    @PostMapping("/addFavorite")
    public String addFavorite(Integer mealId){
       userDao.addFavorite(mealId, userId);
        return "redirect:/mealDetail";
    }

    @GetMapping("/addFavorite")
    public String addFavoriteGet(Integer id){
        //userDao.addFavorite(id, userId);

        return "meal/mealDetail";
    }

    @GetMapping("/deleteFavorite")
    public String deleteFavorite(Integer mealId){
    userDao.deleteFavorite(mealId, userId);
        return "redirect:/mealDetail";
    }

    @GetMapping("/deleteFavoriteFromFavorites")
    public String deleteFavoriteFromFavorites(Integer mealId){
        userDao.deleteFavorite(mealId, userId);
        return "redirect:/favorites";
    }

    @GetMapping("/deleteFavoriteFromHome")
    public String deleteFavoriteFromHome(Integer mealId){
        userDao.deleteFavorite(mealId, userId);
        return "redirect:/home";
    }

}
