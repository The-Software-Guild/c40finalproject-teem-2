package com.team2.mealPlanner.controllers;


import com.team2.mealPlanner.entities.User;
import com.team2.mealPlanner.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
public class HomeController {

    static Logger logger = Logger.getLogger(Controller.class.getName());

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("signup")
    public String signup() {

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processRegistration(User user, Model model)
    {
        User savedUser = userServiceImp.save(user);
        logger.info(savedUser +" Saved user ==");
        return "login";
    }


    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}