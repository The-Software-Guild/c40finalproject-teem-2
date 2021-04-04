package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.MealType;
import com.team2.mealPlanner.entities.PlanMeal;
import com.team2.mealPlanner.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlanMealDB implements PlanMealDao{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public PlanMeal getPlanMealById(int id) {
        try{
            final String SELECT_PLANMEAL_BY_ID = "SELECT * FROM plan_meal WHERE id = ?";

            PlanMeal planMeal =jdbc.queryForObject(SELECT_PLANMEAL_BY_ID, new PlanMealMapper(), id);

            planMeal.setMealType(getMealTypeForPlanMeal(planMeal.getMealTypeId()));

            return planMeal;

        }
        catch (DataAccessException e){
            return null;
        }

    }

    private MealType getMealTypeForPlanMeal(int id){
        try {
            final String SELECT_MEAL_TYPE_BY_ID = "SELECT * FROM mealType WHERE id = ?";
            return jdbc.queryForObject(SELECT_MEAL_TYPE_BY_ID, new MealTypeDB.MealTypeMapper(), id);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public List<PlanMeal> getAllPlanMeals() {
        final String SELECT_ALL_PLANMEALS = "SELECT * FROM plan_meal";
        List<PlanMeal> planMeals = jdbc.query(SELECT_ALL_PLANMEALS, new PlanMealMapper());
        associatePlanMealAndMealType(planMeals);

        return planMeals;
    }

    private void associatePlanMealAndMealType(List<PlanMeal> planMeals){
        for(PlanMeal planMeal: planMeals){
            planMeal.setMealType(getMealTypeForPlanMeal(planMeal.getMealTypeId()));
        }
    }

    @Override
    public PlanMeal addPlanMeal(PlanMeal planMeal) {


        final String INSERT_PLANMEAL = "INSERT INTO plan_meal(planId, mealId, mealTypeId, isCustom) VALUES(?,?,?,?)";
        jdbc.update(INSERT_PLANMEAL, planMeal.getPlanId(), planMeal.getMealId(), planMeal.getMealTypeId(), planMeal.isCustom());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        planMeal.setId(newId);

        return planMeal;
    }

    @Override
    public boolean updatePlanMeal(PlanMeal planMeal) {
        final String UPDATE_PLANMEAL = "UPDATE plan_meal SET planId = ?, mealId = ?, mealTypeId = ?, isCustom = ? WHERE id = ?";

        return jdbc.update(UPDATE_PLANMEAL,planMeal.getPlanId(), planMeal.getMealId(), planMeal.getMealTypeId(), planMeal.isCustom(),
                planMeal.getId()) > 0;
    }

    @Override
    public boolean deletePlanMeal(int id) {
        final String DELETE_PLANMEAL = "DELETE FROM plan_meal WHERE id = ?";
        return jdbc.update(DELETE_PLANMEAL, id) > 0;
    }

    @Override
    public List<PlanMeal> getAllPlanMealsByPlanId(int id) {
        try{
            final String GET_ALL_PLANMEALS_BY_PLAN_ID = "SELECT pm.* FROM plan_meal pm JOIN " +
                    "plan p ON p.id = pm.planId WHERE p.id = ?";
            List<PlanMeal> planMeals = jdbc.query(GET_ALL_PLANMEALS_BY_PLAN_ID, new PlanMealMapper(), id);
            associatePlanMealAndMealType(planMeals);
            return planMeals;
        }catch (DataAccessException e){
            return null;
        }
    }

    public static final class PlanMealMapper implements RowMapper<PlanMeal> {
        @Override
        public PlanMeal mapRow(ResultSet rs, int i) throws SQLException {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setId(rs.getInt("id"));
            planMeal.setMealId(rs.getInt("mealId"));
            planMeal.setPlanId(rs.getInt("planId"));
            planMeal.setMealTypeId(rs.getInt("mealTypeId"));
            planMeal.setCustom(rs.getBoolean("isCustom"));

            return planMeal;
        }
    }

}
