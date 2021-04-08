package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.CustomMeal;
import com.team2.mealPlanner.entities.Plan;
import com.team2.mealPlanner.entities.User;

import java.util.List;

public interface UserDao {

    User getUserById(int id);
    User addUser(User user);
    List<User> getAllUsers();
    boolean updateUser(User user);
    boolean deleteUser(int id);
    List<Integer> getFavoritesById (int id);
    List<CustomMeal> getCustomMealsById (int id);
    List<Plan> getAllPlansById (int id);
    User findUserByLogin(String userName);
    boolean addFavorite(int userId, int mealId);
    void deleteFavorite(int userId, int mealId);
}
