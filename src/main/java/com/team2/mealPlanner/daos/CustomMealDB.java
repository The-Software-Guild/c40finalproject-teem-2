package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.CustomMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomMealDB implements CustomMealDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public CustomMeal getCustomMealById(int id) {
        try {
            final String GET_CUSTOM_MEAL_BY_ID = "SELECT * FROM customMeal WHERE id =?";
            return jdbc.queryForObject(GET_CUSTOM_MEAL_BY_ID, new CustomMealMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<CustomMeal> getAllCustomMeals() {
        final String GET_ALL_CUSTOM_MEALS = "SELECT * FROM customMeal";
        return jdbc.query(GET_ALL_CUSTOM_MEALS, new CustomMealMapper());
    }

    @Override
    public CustomMeal add(CustomMeal customMeal) {
        System.out.println(customMeal.toString());
        final String ADD_CUSTOM_MEAL = "INSERT INTO customMeal(userId, name, ingredients, note) VALUES(?, ?, ?, ?)";
        jdbc.update(ADD_CUSTOM_MEAL,
                customMeal.getUserId(),
                customMeal.getName(),
                customMeal.getIngredients(),
                customMeal.getNote());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        customMeal.setId(newId);
        return customMeal;
    }

    @Override
    public boolean update(CustomMeal customMeal) {
        final String UPDATE_CUSTOM_MEAL = "UPDATE customMeal SET userId = ?, name = ?, ingredients = ?, note = ? WHERE id =?";
        return jdbc.update(UPDATE_CUSTOM_MEAL,
                customMeal.getUserId(),
                customMeal.getName(),
                customMeal.getIngredients(),
                customMeal.getNote(),
                customMeal.getId()) > 0 ;
    }

    @Override
    public boolean delete(int id) {
        final String DELETE_CUSTOM_MEAL = "DELETE FROM customMeal WHERE id = ?";
        return jdbc.update(DELETE_CUSTOM_MEAL, id) > 0;
    }

    public static final class CustomMealMapper implements RowMapper<CustomMeal> {
        @Override
        public CustomMeal mapRow(ResultSet rs, int i) throws SQLException {
            CustomMeal customMeal = new CustomMeal();
            customMeal.setId(rs.getInt("id"));
            customMeal.setName(rs.getString("name"));
            customMeal.setIngredients(rs.getString("ingredients"));
            customMeal.setNote(rs.getString("note"));
            customMeal.setUserId(rs.getInt("userId"));


            return customMeal;
        }
    }
}
