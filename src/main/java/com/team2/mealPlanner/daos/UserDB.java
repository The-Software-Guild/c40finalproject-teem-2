package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.CustomMeal;
import com.team2.mealPlanner.entities.Plan;
import com.team2.mealPlanner.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDB implements UserDao {
    @Autowired
    JdbcTemplate jdbc;


    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public List<Integer> getFavoritesById(int id) {
        return null;
    }

    @Override
    public List<CustomMeal> getCustomMealsById(int id) {
        return null;
    }

    @Override
    public List<Plan> getAllPlansById(int id) {
        return null;
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
}
