package com.team2.mealPlanner.controllers;

import com.team2.mealPlanner.daos.CustomMealDao;
import com.team2.mealPlanner.daos.PlanDao;
import com.team2.mealPlanner.daos.UserDao;
import com.team2.mealPlanner.entities.User;
import com.team2.mealPlanner.services.MealService;
import com.team2.mealPlanner.services.UserServiceImp;
import com.team2.mealPlanner.utils.ApiMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.logging.Logger;

@Controller
public class FavoriteController {

    static Logger logger = Logger.getLogger(FavoriteController.class.getName());

    @Autowired
    UserServiceImp userServiceImp;

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
        User user = userServiceImp.findUserByUsername();

        List<Integer> favorites = userDao.getFavoritesById(user.getId());
        List<ApiMeal> listMeals = mealService.getMealsById(favorites);
        model.addAttribute("meals", listMeals);
        return "meal/favorites";
    }

    @GetMapping("/mealDetail")
    public String displayMeal(Integer id, Model model){

        List<Integer> userFavorites = userDao.getFavoritesById(1);
        if(userFavorites == null){
            userFavorites = new ArrayList<>();
        }
        ApiMeal meal = mealService.getMealById(id).get();


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
        User user = userServiceImp.findUserByUsername();

        userDao.addFavorite(user.getId(), mealId);
        return "redirect:/mealDetail?id="+mealId;
    }

    @GetMapping("/addFavorite")
    public String addFavoriteGet(Integer id){
        User user = userServiceImp.findUserByUsername();

        try{
            userDao.addFavorite(user.getId(), id);
        }catch(Exception e )
        {
            logger.info(e.getMessage());
        }
        return "redirect:/mealDetail?id="+id;
    }



    @GetMapping("/deleteFavorite")
    public String deleteFavorite(Integer mealId, RedirectAttributes redirectAttrs){
        User user = userServiceImp.findUserByUsername();
        userDao.deleteFavorite(user.getId(), mealId);
        return "redirect:/mealDetail?id="+mealId;
    }

    @GetMapping("/deleteFavoriteFromFavorites")
    public String deleteFavoriteFromFavorites(Integer mealId){
        User user = userServiceImp.findUserByUsername();
        userDao.deleteFavorite(user.getId(), mealId);
        return "redirect:/favorites";
    }

    @GetMapping("/deleteFavoriteFromHome")
    public String deleteFavoriteFromHome(Integer mealId){
        User user = userServiceImp.findUserByUsername();
        userDao.deleteFavorite(user.getId(), mealId );
        return "redirect:/home";
    }

}
