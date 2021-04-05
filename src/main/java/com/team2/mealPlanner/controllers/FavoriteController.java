package com.team2.mealPlanner.controllers;

import com.team2.mealPlanner.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FavoriteController {

    int userId = 1;

    @Autowired
    UserDao userDao;
}
