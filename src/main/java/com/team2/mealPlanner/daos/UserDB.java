package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.CustomMeal;
import com.team2.mealPlanner.entities.Plan;
import com.team2.mealPlanner.entities.PlanMeal;
import com.team2.mealPlanner.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDB implements UserDao {
    @Autowired
    JdbcTemplate jdbc;


    @Override
    public User getUserById(int id) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
            user.setCustomMeals(getCustomMealsById(id));
            user.setFavorites(getFavoritesById(id));
            user.setPlans(getAllPlansById(id));
            return user;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public User addUser(User user) {
        final String INSERT_USER = "INSERT INTO user (firstName, lastName, userName, password) " +
                "VALUES (?, ?, ?, ?)";
        jdbc.update(INSERT_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getPassword());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setId(newId);
        //insertCustomMeals(user);
        // insertUserFavorite(user);
        //insertPlans(user);
        return user;
    }

    private void insertCustomMeals(User user) {
        final String INSERT_CUSTOM_MEAL = "INSERT INTO customMeal (userId, name, ingredients, note) " +
                "VALUES (?, ?, ?, ?)";
        for (CustomMeal customMeal : user.getCustomMeals()) {
            jdbc.update(INSERT_CUSTOM_MEAL,
                    user.getId(),
                    customMeal.getName(),
                    customMeal.getIngredients(),
                    customMeal.getNote());
        }
    }

    private void insertUserFavorite(User user) {
        final String INSERT_USER_FAVORITE = "INSERT INTO user_favorite (mealId, userId) VALUES (?, ?)";
        for (Integer favoriteId : user.getFavorites()) {
            jdbc.update(INSERT_USER_FAVORITE,
                    favoriteId,
                    user.getId());
        }
    }

    private void insertPlans(User user) {
        final String INSERT_PLAN = "INSERT INTO plan (date, userId) VALUES (?, ?)";
        final String INSERT_PLAN_MEAL = "INSERT INTO plan_meal (planId, mealId, mealTypeId, isCustom) " +
                "VALUES (?, ?, ?)";
        for (Plan plan : user.getPlans()) {
            jdbc.update(INSERT_PLAN,
                    plan.getDate(),
                    user.getId());
            if(plan.getPlanMeals() != null){
                for (PlanMeal planMeal : plan.getPlanMeals()) {
                    jdbc.update(INSERT_PLAN_MEAL,
                            plan.getId(),
                            planMeal.getMealId(),
                            planMeal.getMealType().getId(),
                            planMeal.isCustom());
                }
            }

        }
    }

    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USERS = "SELECT * FROM user";
        List<User> users = jdbc.query(SELECT_ALL_USERS, new UserMapper());

        for (User user : users) {
            user.setPlans(getAllPlansById(user.getId()));
            user.setCustomMeals(getCustomMealsById(user.getId()));
            user.setFavorites(getFavoritesById(user.getId()));
        }

        return users;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        final String UPDATE_USER = "UPDATE user SET firstName = ?, lastName = ?, userName = ?, " +
                "password = ? WHERE id = ?";
        if (jdbc.update(UPDATE_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getPassword(),
                user.getId()) <= 0) return false;

        final String DELETE_USER_FAVORITE = "DELETE FROM user_favorite WHERE UserId = ?";
        jdbc.update(DELETE_USER_FAVORITE, user.getId());

        final String DELETE_CUSTOM_MEAL = "DELETE FROM customMeal WHERE UserId = ?";
        jdbc.update(DELETE_CUSTOM_MEAL, user.getId());

        final String DELETE_PLAN_MEAL = "DELETE FROM plan_meal WHERE id = ?";
        final String DELETE_PLAN_MEAL_BY_PLANID = "DELETE FROM plan_meal WHERE planId = ?";
        final String DELETE_PLAN = "DELETE FROM plan WHERE id = ?";
        for (Plan plan : user.getPlans()) {
            jdbc.update(DELETE_PLAN_MEAL_BY_PLANID, plan.getId());
            jdbc.update(DELETE_PLAN, plan.getId());
        }

        insertCustomMeals(user);
        insertUserFavorite(user);
        insertPlans(user);

        return true;
    }

    @Override
    @Transactional
    public boolean deleteUser(int id) {
        final String DELETE_USER_FAVORITE = "DELETE FROM user_favorite WHERE UserId = ?";
        jdbc.update(DELETE_USER_FAVORITE, id);

        final String DELETE_CUSTOM_MEAL = "DELETE FROM customMeal WHERE UserId = ?";
        jdbc.update(DELETE_CUSTOM_MEAL, id);

        final String DELETE_PLAN_MEAL = "DELETE FROM plan_meal WHERE id = ?";
        final String DELETE_PLAN_MEAL_BY_PLANID = "DELETE FROM plan_meal WHERE planId = ?";
        final String DELETE_PLAN = "DELETE FROM plan WHERE id = ?";
        for (Plan plan : getAllPlansById(id)) {
            jdbc.update(DELETE_PLAN_MEAL_BY_PLANID, plan.getId());
            jdbc.update(DELETE_PLAN, plan.getId());
        }

        final String DELETE_USER = "DELETE FROM user WHERE id = ?";
        return jdbc.update(DELETE_USER, id) > 0;
    }

    @Override
    public List<Integer> getFavoritesById(int id) {
        final String SELECT_FAVORITES_FOR_USER = "SELECT mealId FROM user_favorite WHERE userId = ?";
        return jdbc.query(SELECT_FAVORITES_FOR_USER, new FavoriteMapper(), id);
    }

    @Override
    public List<CustomMeal> getCustomMealsById(int id) {
        final String SELECT_CUSTOM_MEALS_FOR_USER = "SELECT * FROM customMeal WHERE userId = ?";
        return jdbc.query(SELECT_CUSTOM_MEALS_FOR_USER, new CustomMealDB.CustomMealMapper(), id);
    }

    @Override
    public List<Plan> getAllPlansById(int id) {
        final String SELECT_PLANS_FOR_USER = "SELECT * FROM plan WHERE userId = ?";
        return jdbc.query(SELECT_PLANS_FOR_USER, new PlanDB.PlanMapper(), id);
    }

    @Override
    public User findUserByLogin(String userName) {
        String SELECT_USER_BY_UN_PWD = " SELECT * FROM user where userName =?";
        return  jdbc.queryForObject(SELECT_USER_BY_UN_PWD,new UserMapper(),userName);
    }

    public static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("userName"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setPassword(rs.getString("password"));

            return user;
        }
    }

    private static final class FavoriteMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("mealId");
        }
    }
}
