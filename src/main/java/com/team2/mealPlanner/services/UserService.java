package com.team2.mealPlanner.services;

import com.team2.mealPlanner.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {

    User save(User user);
}
