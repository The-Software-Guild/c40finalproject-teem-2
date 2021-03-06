package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlanDB implements PlanDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Plan getPlanById(int id) {
        try {
            final String SELECT_PLAN_BY_ID = "SELECT * FROM plan WHERE id = ?";
            Plan plan = jdbc.queryForObject(SELECT_PLAN_BY_ID, new PlanMapper(), id);
            return plan;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Plan> getAllPlans() {
        final String SELECT_ALL_PLANS = "SELECT * FROM plan";
        List<Plan> plans = jdbc.query(SELECT_ALL_PLANS, new PlanMapper());
        return plans;
    }

    @Override
    public Plan addPlan(Plan plan) {
        final String INSERT_PLAN = "INSERT INTO plan(date, userId) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_PLAN,
                plan.getDate(),
                plan.getIdUser()
                );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        plan.setId(newId);
        return plan;
    }

    @Override
    @Transactional
    public boolean updatePlan(Plan plan) {
        final String DELETE_PLAN_MEALS = "delete from plan_meal where planId = ?";
        jdbc.update(DELETE_PLAN_MEALS, plan.getId());

        final String UPDATE_PLAN = "UPDATE plan SET date= ?"
                + "WHERE id = ?";
        return jdbc.update(UPDATE_PLAN,
                plan.getDate(),
                plan.getId()) > 0 ;


    }

    @Override
    @Transactional
    public boolean deletePlan(int id) {
        final String DELETE_PLAN_MEALS = "delete from plan_meal where planId = ?";
        final String UPDATE_PLAN = "delete from plan WHERE id = ?";
        jdbc.update(DELETE_PLAN_MEALS,id);
        return jdbc.update(UPDATE_PLAN, id) > 0;
    }


    public static final class PlanMapper implements RowMapper<Plan> {
        @Override
        public Plan mapRow(ResultSet rs, int i) throws SQLException {
            Plan plan = new Plan();
            plan.setId(rs.getInt("id"));
            plan.setDate(rs.getDate("date").toLocalDate());

            return plan;
        }
    }
}
