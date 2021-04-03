package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.Plan;
import com.team2.mealPlanner.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlanDB implements PlanDao {

    JdbcTemplate jdbc;

    @Override
    public Plan getPlanById(int id) {
        return null;
    }

    @Override
    public List<Plan> getAllPlans() {
        return null;
    }

    @Override
    public Plan addPlan(Plan plan) {
        return null;
    }

    @Override
    public boolean updatePlan(Plan plan) {
        return false;
    }

    @Override
    public boolean deletePlan(int id) {
        return false;
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
