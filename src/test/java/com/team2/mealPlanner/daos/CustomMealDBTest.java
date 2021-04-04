package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.CustomMeal;
import com.team2.mealPlanner.entities.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomMealDBTest {

    @Autowired
    CustomMealDao customMealDao;

    @Autowired
    MealTypeDao mealTypeDao;

    @Autowired
    PlanDao planDao;

    @Autowired
    UserDao userDao;

    public CustomMealDBTest(){

    }

    @BeforeClass
    public static void setUpClass(){

    }
    @AfterClass
    public static void tearDownClass(){

    }

    @BeforeEach
    public void setUp(){

        List<User> users = userDao.getAllUsers();
        for (User user: users) {
            userDao.deleteUser(user.getId());
        }
        List<CustomMeal> customMeals = customMealDao.getAllCustomMeals();
        for(CustomMeal customMeal : customMeals){
            customMealDao.delete(customMeal.getId());
        }

    }

    @Test
    void addAndGetCustomMeal() {
        User user = new User();
        user.setUserName("User One");
        user.setFirstName("User First Name One");
        user.setLastName("User Last Name One");
        user.setPassword("User Password One");
        user = userDao.addUser(user);

        CustomMeal customMeal = new CustomMeal();
        customMeal.setUserId(user.getId());
        customMeal.setName("Test Name");
        customMeal.setIngredients("Test Ingredients");
        customMeal.setNote("Test Note");
        customMeal = customMealDao.add(customMeal);
        CustomMeal fromDao = customMealDao.getCustomMealById(customMeal.getId());

        assertEquals(customMeal, fromDao);
    }

    @Test
    void getAllCustomMeals() {
        User user = new User();
        user.setUserName("User One");
        user.setFirstName("User First Name One");
        user.setLastName("User Last Name One");
        user.setPassword("User Password One");
        user = userDao.addUser(user);

        CustomMeal customMeal = new CustomMeal();
        customMeal.setUserId(user.getId());
        customMeal.setName("Test Name");
        customMeal.setIngredients("Test Ingredients");
        customMeal.setNote("Test Note");
        customMeal = customMealDao.add(customMeal);

        CustomMeal customMeal1 = new CustomMeal();
        customMeal1.setUserId(user.getId());
        customMeal1.setName("Test Name One");
        customMeal1.setIngredients("Test Ingredients One");
        customMeal1.setNote("Test Note One");
        customMeal1 = customMealDao.add(customMeal1);

        List<CustomMeal> customMealList = customMealDao.getAllCustomMeals();
        assertEquals(2, customMealList.size());
        assertTrue(customMealList.contains(customMeal));
        assertTrue(customMealList.contains(customMeal1));
    }

    @Test
    void update() {
        User user = new User();
        user.setUserName("User One");
        user.setFirstName("User First Name One");
        user.setLastName("User Last Name One");
        user.setPassword("User Password One");
        user = userDao.addUser(user);

        CustomMeal customMeal = new CustomMeal();
        customMeal.setUserId(user.getId());
        customMeal.setName("Test Name");
        customMeal.setIngredients("Test Ingredients");
        customMeal.setNote("Test Note");
        customMeal = customMealDao.add(customMeal);

        CustomMeal fromDao = customMealDao.getCustomMealById(customMeal.getId());
        assertEquals(customMeal, fromDao);

        customMeal.setName("New Test Name");
        customMealDao.update(customMeal);

        assertNotEquals(customMeal, fromDao);

        fromDao = customMealDao.getCustomMealById((customMeal.getId()));
        assertEquals(customMeal, fromDao);
    }

    @Test
    void delete() {
        User user = new User();
        user.setUserName("User One");
        user.setFirstName("User First Name One");
        user.setLastName("User Last Name One");
        user.setPassword("User Password One");
        user = userDao.addUser(user);

        CustomMeal customMeal = new CustomMeal();
        customMeal.setUserId(user.getId());
        customMeal.setName("Test Name");
        customMeal.setIngredients("Test Ingredients");
        customMeal.setNote("Test Note");
        customMeal = customMealDao.add(customMeal);

        CustomMeal fromDao = customMealDao.getCustomMealById(customMeal.getId());
        assertEquals(customMeal, fromDao);

        customMealDao.delete(customMeal.getId());
        fromDao = customMealDao.getCustomMealById(customMeal.getId());
        assertNull(fromDao);
    }
}