package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.MealType;
import com.team2.mealPlanner.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MealTypeDB implements MealTypeDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public MealType getMealTypeById(int id) {
        return null;
    }

    @Override
    public List<MealType> getAllMealTypes() {
        return null;
    }

    @Override
    public MealType addMealType(MealType mealType) {
        return null;
    }

    @Override
    public boolean updateMealType(MealType mealType) {
        return false;
    }

    @Override
    public boolean deleteMealType(int id) {
        return false;
    }

    public static final class MealTypeMapper implements RowMapper<MealType> {
        @Override
        public MealType mapRow(ResultSet rs, int i) throws SQLException {
            MealType mealType = new MealType();
            mealType.setId(rs.getInt("id"));
            mealType.setName(rs.getString("name"));

            return mealType;
        }
    }
}
