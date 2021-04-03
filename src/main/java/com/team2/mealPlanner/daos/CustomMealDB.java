package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.CustomMeal;
import com.team2.mealPlanner.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @Override
    public List<CustomMeal> getAllCustomMeals() {
        return null;
    }

    @Override
    public CustomMeal add(CustomMeal customMeal) {
        return null;
    }

    @Override
    public boolean update(CustomMeal customMeal) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public static final class CustomMealMapper implements RowMapper<CustomMeal> {
        @Override
        public CustomMeal mapRow(ResultSet rs, int i) throws SQLException {
            CustomMeal customMeal = new CustomMeal();
            customMeal.setId(rs.getInt("id"));
            customMeal.setName(rs.getString("name"));
            customMeal.setIngredients(rs.getString("ingredients"));
            customMeal.setNote(rs.getString("note"));


            return customMeal;
        }
    }
}
