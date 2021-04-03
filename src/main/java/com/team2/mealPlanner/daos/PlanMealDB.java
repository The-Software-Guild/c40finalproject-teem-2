package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.PlanMeal;
import com.team2.mealPlanner.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlanMealDB implements PlanMealDao{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public PlanMeal getPlanMealById(int id) {
        return null;
    }

    @Override
    public List<PlanMeal> getAllPlanMeals() {
        return null;
    }

    @Override
    public PlanMeal addPlanMeal(PlanMeal planMeal) {
        return null;
    }

    @Override
    public boolean updatePlanMeal(PlanMeal planMeal) {
        return false;
    }

    @Override
    public boolean deletePlanMeal(int id) {
        return false;
    }

    public static final class PlanMealMapper implements RowMapper<PlanMeal> {
        @Override
        public PlanMeal mapRow(ResultSet rs, int i) throws SQLException {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setId(rs.getInt("id"));
            planMeal.setCustom(rs.getBoolean("isCustom"));

            return planMeal;
        }
    }
}
