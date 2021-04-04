package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.MealType;
import com.team2.mealPlanner.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try{
            final String SELECT_MEALTYPE_BY_ID = "SELECT * FROM mealType WHERE id =?";
            return jdbc.queryForObject(SELECT_MEALTYPE_BY_ID, new MealTypeMapper(), id);
        }
        catch(DataAccessException e){
            return null;
        }
    }

    @Override
    public List<MealType> getAllMealTypes() {
        final String SELECT_ALL_MEALTYPES = "SELECT * FROM mealType";
        return jdbc.query(SELECT_ALL_MEALTYPES, new MealTypeMapper());
    }

    @Override
    public MealType addMealType(MealType mealType) {
        final String INSERT_MEALTYPE = "INSERT INTO mealType(id, name) VALUES(?,?)";
        jdbc.update(INSERT_MEALTYPE, mealType.getId(), mealType.getName());
        return mealType;
    }

    @Override
    public boolean updateMealType(MealType mealType) {
        final String UPDATE_MEALTYPE = "UPDATE mealType SET name = ? WHERE id = ?";
        return jdbc.update(UPDATE_MEALTYPE, mealType.getName(), mealType.getId()) > 0;
    }

    @Override
    public boolean deleteMealType(int id) {
        final String DELETE_MEALTYPE = "DELETE FROM mealType WHERE id = ?";
        return jdbc.update(DELETE_MEALTYPE, id) > 0;
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
