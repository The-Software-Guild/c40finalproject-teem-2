package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDBTest {

    @Autowired
    CustomMealDao customMealDao;

    @Autowired
    MealTypeDao mealTypeDao;

    @Autowired
    PlanDao planDao;

    @Autowired
    PlanMealDao planMealDao;

    @Autowired
    UserDao userDao;

    @BeforeEach
    void setUp() {
        List<User> users = userDao.getAllUsers();
        for (User user: users) {
            userDao.deleteUser(user.getId());
        }

        List<MealType> mealTypes = mealTypeDao.getAllMealTypes();
        for (MealType mealType: mealTypes) {
            mealTypeDao.deleteMealType(mealType.getId());
        }
    }

    @Test
    void getUserById() {
        CustomMeal customMeal = new CustomMeal();
        customMeal.setName("Meal Name");
        customMeal.setIngredients("Salt");
        customMeal.setNote("Note");
        customMeal = customMealDao.add(customMeal);

        List<CustomMeal> customMeals = new ArrayList<>();
        customMeals.add(customMeal);

        List<Integer> favorites = new ArrayList<>();
        favorites.add(customMeal.getId());

        MealType mealType = new MealType();
        mealType.setName("breakfast");
        mealType = mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setMealId(customMeal.getId());
        planMeal.setCustom(true);
        planMeal.setMealType(mealType);
        planMeal = planMealDao.addPlanMeal(planMeal);

        List<PlanMeal> planMeals = new ArrayList<>();
        planMeals.add(planMeal);

        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan.setPlanMeals(planMeals);
        plan = planDao.addPlan(plan);

        List<Plan> plans = new ArrayList<>();
        plans.add(plan);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("root");
        user.setPassword("rootroot");
        user.setFavorites(favorites);
        user.setPlans(plans);
        user.setCustomMeals(customMeals);
        user = userDao.addUser(user);

        User fromDao = userDao.getUserById(user.getId());
        assertEquals(user, fromDao);
    }

    @Test
    void addUser() {
        CustomMeal customMeal = new CustomMeal();
        customMeal.setName("Meal Name");
        customMeal.setIngredients("Salt");
        customMeal.setNote("Note");
        customMeal = customMealDao.add(customMeal);

        List<CustomMeal> customMeals = new ArrayList<>();
        customMeals.add(customMeal);

        List<Integer> favorites = new ArrayList<>();
        favorites.add(customMeal.getId());

        MealType mealType = new MealType();
        mealType.setName("breakfast");
        mealType = mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setMealId(customMeal.getId());
        planMeal.setCustom(true);
        planMeal.setMealType(mealType);
        planMeal = planMealDao.addPlanMeal(planMeal);

        List<PlanMeal> planMeals = new ArrayList<>();
        planMeals.add(planMeal);

        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan.setPlanMeals(planMeals);
        plan = planDao.addPlan(plan);

        List<Plan> plans = new ArrayList<>();
        plans.add(plan);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("root");
        user.setPassword("rootroot");
        user.setFavorites(favorites);
        user.setPlans(plans);
        user.setCustomMeals(customMeals);
        user = userDao.addUser(user);

        User fromDao = userDao.getUserById(user.getId());
        assertEquals(user, fromDao);
    }

    @Test
    void getAllUsers() {
        CustomMeal customMeal = new CustomMeal();
        customMeal.setName("Meal Name");
        customMeal.setIngredients("Salt");
        customMeal.setNote("Note");
        customMeal = customMealDao.add(customMeal);

        CustomMeal customMeal0 = new CustomMeal();
        customMeal0.setName("Meal Name 0");
        customMeal0.setIngredients("Sugar");
        customMeal0.setNote("Another Note");
        customMeal0 = customMealDao.add(customMeal0);

        List<CustomMeal> customMeals = new ArrayList<>();
        customMeals.add(customMeal);

        List<CustomMeal> customMeals0 = new ArrayList<>();
        customMeals0.add(customMeal0);

        List<Integer> favorites = new ArrayList<>();
        favorites.add(customMeal.getId());

        List<Integer> favorites0 = new ArrayList<>();
        favorites0.add(customMeal0.getId());

        MealType mealType = new MealType();
        mealType.setName("breakfast");
        mealType = mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setMealId(customMeal.getId());
        planMeal.setCustom(true);
        planMeal.setMealType(mealType);
        planMeal = planMealDao.addPlanMeal(planMeal);

        PlanMeal planMeal0 = new PlanMeal();
        planMeal0.setMealId(customMeal0.getId());
        planMeal0.setCustom(true);
        planMeal0.setMealType(mealType);
        planMeal0 = planMealDao.addPlanMeal(planMeal0);

        List<PlanMeal> planMeals = new ArrayList<>();
        planMeals.add(planMeal);

        List<PlanMeal> planMeals0 = new ArrayList<>();
        planMeals0.add(planMeal0);

        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan.setPlanMeals(planMeals);
        plan = planDao.addPlan(plan);

        Plan plan0 = new Plan();
        plan0.setDate(LocalDate.now());
        plan0.setPlanMeals(planMeals0);
        plan0 = planDao.addPlan(plan0);

        List<Plan> plans = new ArrayList<>();
        plans.add(plan);

        List<Plan> plans0 = new ArrayList<>();
        plans0.add(plan0);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("root");
        user.setPassword("rootroot");
        user.setFavorites(favorites);
        user.setPlans(plans);
        user.setCustomMeals(customMeals);
        user = userDao.addUser(user);

        User user0 = new User();
        user0.setFirstName("Jane");
        user0.setLastName("Doe");
        user0.setUserName("root");
        user0.setPassword("rootroot");
        user0.setFavorites(favorites0);
        user0.setPlans(plans0);
        user0.setCustomMeals(customMeals0);
        user0 = userDao.addUser(user0);

        List<User> users = userDao.getAllUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains(user));
        assertTrue(users.contains(user0));
    }

    @Test
    void updateUser() {
        CustomMeal customMeal = new CustomMeal();
        customMeal.setName("Meal Name");
        customMeal.setIngredients("Salt");
        customMeal.setNote("Note");
        customMeal = customMealDao.add(customMeal);

        List<CustomMeal> customMeals = new ArrayList<>();
        customMeals.add(customMeal);

        List<Integer> favorites = new ArrayList<>();
        favorites.add(customMeal.getId());

        MealType mealType = new MealType();
        mealType.setName("breakfast");
        mealType = mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setMealId(customMeal.getId());
        planMeal.setCustom(true);
        planMeal.setMealType(mealType);
        planMeal = planMealDao.addPlanMeal(planMeal);

        List<PlanMeal> planMeals = new ArrayList<>();
        planMeals.add(planMeal);

        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan.setPlanMeals(planMeals);
        plan = planDao.addPlan(plan);

        List<Plan> plans = new ArrayList<>();
        plans.add(plan);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("root");
        user.setPassword("rootroot");
        user.setFavorites(favorites);
        user.setPlans(plans);
        user.setCustomMeals(customMeals);
        user = userDao.addUser(user);

        User fromDao = userDao.getUserById(user.getId());
        assertEquals(user, fromDao);

        CustomMeal customMeal0 = new CustomMeal();
        customMeal0.setName("Meal Name 0");
        customMeal0.setIngredients("Sugar");
        customMeal0.setNote("Another Note");
        customMeal0 = customMealDao.add(customMeal0);

        customMeals.add(customMeal0);
        user.setCustomMeals(customMeals);

        userDao.updateUser(user);

        assertNotEquals(user, fromDao);

        fromDao = userDao.getUserById(user.getId());
        assertEquals(user, fromDao);
    }

    @Test
    void deleteUser() {
        CustomMeal customMeal = new CustomMeal();
        customMeal.setName("Meal Name");
        customMeal.setIngredients("Salt");
        customMeal.setNote("Note");
        customMeal = customMealDao.add(customMeal);

        List<CustomMeal> customMeals = new ArrayList<>();
        customMeals.add(customMeal);

        List<Integer> favorites = new ArrayList<>();
        favorites.add(customMeal.getId());

        MealType mealType = new MealType();
        mealType.setName("breakfast");
        mealType = mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setMealId(customMeal.getId());
        planMeal.setCustom(true);
        planMeal.setMealType(mealType);
        planMeal = planMealDao.addPlanMeal(planMeal);

        List<PlanMeal> planMeals = new ArrayList<>();
        planMeals.add(planMeal);

        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan.setPlanMeals(planMeals);
        plan = planDao.addPlan(plan);

        List<Plan> plans = new ArrayList<>();
        plans.add(plan);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("root");
        user.setPassword("rootroot");
        user.setFavorites(favorites);
        user.setPlans(plans);
        user.setCustomMeals(customMeals);
        user = userDao.addUser(user);

        User fromDao = userDao.getUserById(user.getId());
        assertEquals(user, fromDao);

        userDao.deleteUser(user.getId());

        fromDao = userDao.getUserById(user.getId());
        assertNull(fromDao);
    }

    @Test
    void getFavoritesById() {
        CustomMeal customMeal = new CustomMeal();
        customMeal.setName("Meal Name");
        customMeal.setIngredients("Salt");
        customMeal.setNote("Note");
        customMeal = customMealDao.add(customMeal);

        CustomMeal customMeal0 = new CustomMeal();
        customMeal0.setName("Meal Name 0");
        customMeal0.setIngredients("Sugar");
        customMeal0.setNote("Another Note");
        customMeal0 = customMealDao.add(customMeal0);

        CustomMeal customMeal1 = new CustomMeal();
        customMeal1.setName("Meal Name 1");
        customMeal1.setIngredients("Pepper");
        customMeal1.setNote("Another another Note");
        customMeal1 = customMealDao.add(customMeal1);

        List<CustomMeal> customMeals = new ArrayList<>();
        customMeals.add(customMeal);

        List<CustomMeal> customMeals0 = new ArrayList<>();
        customMeals0.add(customMeal0);

        List<CustomMeal> customMeals1 = new ArrayList<>();
        customMeals1.add(customMeal1);

        List<Integer> favorites = new ArrayList<>();
        favorites.add(customMeal.getId());

        List<Integer> favorites0 = new ArrayList<>();
        favorites0.add(customMeal0.getId());

        List<Integer> favorites1 = new ArrayList<>();
        favorites1.add(customMeal1.getId());

        MealType mealType = new MealType();
        mealType.setName("breakfast");
        mealType = mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setMealId(customMeal.getId());
        planMeal.setCustom(true);
        planMeal.setMealType(mealType);
        planMeal = planMealDao.addPlanMeal(planMeal);

        PlanMeal planMeal0 = new PlanMeal();
        planMeal0.setMealId(customMeal0.getId());
        planMeal0.setCustom(true);
        planMeal0.setMealType(mealType);
        planMeal0 = planMealDao.addPlanMeal(planMeal0);

        PlanMeal planMeal1 = new PlanMeal();
        planMeal1.setMealId(customMeal0.getId());
        planMeal1.setCustom(true);
        planMeal1.setMealType(mealType);
        planMeal1 = planMealDao.addPlanMeal(planMeal1);

        List<PlanMeal> planMeals = new ArrayList<>();
        planMeals.add(planMeal);

        List<PlanMeal> planMeals0 = new ArrayList<>();
        planMeals0.add(planMeal0);

        List<PlanMeal> planMeals1 = new ArrayList<>();
        planMeals1.add(planMeal1);

        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan.setPlanMeals(planMeals);
        plan = planDao.addPlan(plan);

        Plan plan0 = new Plan();
        plan0.setDate(LocalDate.now());
        plan0.setPlanMeals(planMeals0);
        plan0 = planDao.addPlan(plan0);

        Plan plan1 = new Plan();
        plan1.setDate(LocalDate.now());
        plan1.setPlanMeals(planMeals1);
        plan1 = planDao.addPlan(plan1);

        List<Plan> plans = new ArrayList<>();
        plans.add(plan);

        List<Plan> plans0 = new ArrayList<>();
        plans0.add(plan0);

        List<Plan> plans1 = new ArrayList<>();
        plans1.add(plan1);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("root");
        user.setPassword("rootroot");
        user.setFavorites(favorites);
        user.setPlans(plans);
        user.setCustomMeals(customMeals);
        user = userDao.addUser(user);

        User user0 = new User();
        user0.setFirstName("Jane");
        user0.setLastName("Doe");
        user0.setUserName("root");
        user0.setPassword("rootroot");
        user0.setFavorites(favorites0);
        user0.setPlans(plans0);
        user0.setCustomMeals(customMeals0);
        user0 = userDao.addUser(user0);

        User user1 = new User();
        user1.setFirstName("James");
        user1.setLastName("Doe");
        user1.setUserName("root");
        user1.setPassword("rootroot");
        user1.setFavorites(favorites0);
        user1.setPlans(plans0);
        user1.setCustomMeals(customMeals0);
        user1 = userDao.addUser(user1);

        List<Integer> favoritesFromDao = userDao.getFavoritesById(user0.getId());
        assertEquals(1, favoritesFromDao.size());
        assertTrue(favoritesFromDao.contains(customMeal0.getId()));
        assertFalse(favoritesFromDao.contains(customMeal.getId()));
        assertFalse(favoritesFromDao.contains(customMeal1.getId()));
    }

    @Test
    void getCustomMealsById() {
        CustomMeal customMeal = new CustomMeal();
        customMeal.setName("Meal Name");
        customMeal.setIngredients("Salt");
        customMeal.setNote("Note");
        customMeal = customMealDao.add(customMeal);

        CustomMeal customMeal0 = new CustomMeal();
        customMeal0.setName("Meal Name 0");
        customMeal0.setIngredients("Sugar");
        customMeal0.setNote("Another Note");
        customMeal0 = customMealDao.add(customMeal0);

        CustomMeal customMeal1 = new CustomMeal();
        customMeal1.setName("Meal Name 1");
        customMeal1.setIngredients("Pepper");
        customMeal1.setNote("Another another Note");
        customMeal1 = customMealDao.add(customMeal1);

        List<CustomMeal> customMeals = new ArrayList<>();
        customMeals.add(customMeal);

        List<CustomMeal> customMeals0 = new ArrayList<>();
        customMeals0.add(customMeal0);

        List<CustomMeal> customMeals1 = new ArrayList<>();
        customMeals1.add(customMeal1);

        List<Integer> favorites = new ArrayList<>();
        favorites.add(customMeal.getId());

        List<Integer> favorites0 = new ArrayList<>();
        favorites0.add(customMeal0.getId());

        List<Integer> favorites1 = new ArrayList<>();
        favorites1.add(customMeal1.getId());

        MealType mealType = new MealType();
        mealType.setName("breakfast");
        mealType = mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setMealId(customMeal.getId());
        planMeal.setCustom(true);
        planMeal.setMealType(mealType);
        planMeal = planMealDao.addPlanMeal(planMeal);

        PlanMeal planMeal0 = new PlanMeal();
        planMeal0.setMealId(customMeal0.getId());
        planMeal0.setCustom(true);
        planMeal0.setMealType(mealType);
        planMeal0 = planMealDao.addPlanMeal(planMeal0);

        PlanMeal planMeal1 = new PlanMeal();
        planMeal1.setMealId(customMeal0.getId());
        planMeal1.setCustom(true);
        planMeal1.setMealType(mealType);
        planMeal1 = planMealDao.addPlanMeal(planMeal1);

        List<PlanMeal> planMeals = new ArrayList<>();
        planMeals.add(planMeal);

        List<PlanMeal> planMeals0 = new ArrayList<>();
        planMeals0.add(planMeal0);

        List<PlanMeal> planMeals1 = new ArrayList<>();
        planMeals1.add(planMeal1);

        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan.setPlanMeals(planMeals);
        plan = planDao.addPlan(plan);

        Plan plan0 = new Plan();
        plan0.setDate(LocalDate.now());
        plan0.setPlanMeals(planMeals0);
        plan0 = planDao.addPlan(plan0);

        Plan plan1 = new Plan();
        plan1.setDate(LocalDate.now());
        plan1.setPlanMeals(planMeals1);
        plan1 = planDao.addPlan(plan1);

        List<Plan> plans = new ArrayList<>();
        plans.add(plan);

        List<Plan> plans0 = new ArrayList<>();
        plans0.add(plan0);

        List<Plan> plans1 = new ArrayList<>();
        plans1.add(plan1);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("root");
        user.setPassword("rootroot");
        user.setFavorites(favorites);
        user.setPlans(plans);
        user.setCustomMeals(customMeals);
        user = userDao.addUser(user);

        User user0 = new User();
        user0.setFirstName("Jane");
        user0.setLastName("Doe");
        user0.setUserName("root");
        user0.setPassword("rootroot");
        user0.setFavorites(favorites0);
        user0.setPlans(plans0);
        user0.setCustomMeals(customMeals0);
        user0 = userDao.addUser(user0);

        User user1 = new User();
        user1.setFirstName("James");
        user1.setLastName("Doe");
        user1.setUserName("root");
        user1.setPassword("rootroot");
        user1.setFavorites(favorites0);
        user1.setPlans(plans0);
        user1.setCustomMeals(customMeals0);
        user1 = userDao.addUser(user1);

        List<CustomMeal> customMealsFromDao = userDao.getCustomMealsById(user0.getId());
        assertEquals(1, customMealsFromDao.size());
        assertTrue(customMealsFromDao.contains(customMeal0));
        assertFalse(customMealsFromDao.contains(customMeal));
        assertFalse(customMealsFromDao.contains(customMeal1));
    }

    @Test
    void getAllPlansById() {
        CustomMeal customMeal = new CustomMeal();
        customMeal.setName("Meal Name");
        customMeal.setIngredients("Salt");
        customMeal.setNote("Note");
        customMeal = customMealDao.add(customMeal);

        CustomMeal customMeal0 = new CustomMeal();
        customMeal0.setName("Meal Name 0");
        customMeal0.setIngredients("Sugar");
        customMeal0.setNote("Another Note");
        customMeal0 = customMealDao.add(customMeal0);

        CustomMeal customMeal1 = new CustomMeal();
        customMeal1.setName("Meal Name 1");
        customMeal1.setIngredients("Pepper");
        customMeal1.setNote("Another another Note");
        customMeal1 = customMealDao.add(customMeal1);

        List<CustomMeal> customMeals = new ArrayList<>();
        customMeals.add(customMeal);

        List<CustomMeal> customMeals0 = new ArrayList<>();
        customMeals0.add(customMeal0);

        List<CustomMeal> customMeals1 = new ArrayList<>();
        customMeals1.add(customMeal1);

        List<Integer> favorites = new ArrayList<>();
        favorites.add(customMeal.getId());

        List<Integer> favorites0 = new ArrayList<>();
        favorites0.add(customMeal0.getId());

        List<Integer> favorites1 = new ArrayList<>();
        favorites1.add(customMeal1.getId());

        MealType mealType = new MealType();
        mealType.setName("breakfast");
        mealType = mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setMealId(customMeal.getId());
        planMeal.setCustom(true);
        planMeal.setMealType(mealType);
        planMeal = planMealDao.addPlanMeal(planMeal);

        PlanMeal planMeal0 = new PlanMeal();
        planMeal0.setMealId(customMeal0.getId());
        planMeal0.setCustom(true);
        planMeal0.setMealType(mealType);
        planMeal0 = planMealDao.addPlanMeal(planMeal0);

        PlanMeal planMeal1 = new PlanMeal();
        planMeal1.setMealId(customMeal0.getId());
        planMeal1.setCustom(true);
        planMeal1.setMealType(mealType);
        planMeal1 = planMealDao.addPlanMeal(planMeal1);

        List<PlanMeal> planMeals = new ArrayList<>();
        planMeals.add(planMeal);

        List<PlanMeal> planMeals0 = new ArrayList<>();
        planMeals0.add(planMeal0);

        List<PlanMeal> planMeals1 = new ArrayList<>();
        planMeals1.add(planMeal1);

        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan.setPlanMeals(planMeals);
        plan = planDao.addPlan(plan);

        Plan plan0 = new Plan();
        plan0.setDate(LocalDate.now());
        plan0.setPlanMeals(planMeals0);
        plan0 = planDao.addPlan(plan0);

        Plan plan1 = new Plan();
        plan1.setDate(LocalDate.now());
        plan1.setPlanMeals(planMeals1);
        plan1 = planDao.addPlan(plan1);

        List<Plan> plans = new ArrayList<>();
        plans.add(plan);

        List<Plan> plans0 = new ArrayList<>();
        plans0.add(plan0);

        List<Plan> plans1 = new ArrayList<>();
        plans1.add(plan1);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("root");
        user.setPassword("rootroot");
        user.setFavorites(favorites);
        user.setPlans(plans);
        user.setCustomMeals(customMeals);
        user = userDao.addUser(user);

        User user0 = new User();
        user0.setFirstName("Jane");
        user0.setLastName("Doe");
        user0.setUserName("root");
        user0.setPassword("rootroot");
        user0.setFavorites(favorites0);
        user0.setPlans(plans0);
        user0.setCustomMeals(customMeals0);
        user0 = userDao.addUser(user0);

        User user1 = new User();
        user1.setFirstName("James");
        user1.setLastName("Doe");
        user1.setUserName("root");
        user1.setPassword("rootroot");
        user1.setFavorites(favorites0);
        user1.setPlans(plans0);
        user1.setCustomMeals(customMeals0);
        user1 = userDao.addUser(user1);

        List<Plan> plansFromDao = userDao.getAllPlansById(user0.getId());
        assertEquals(1, plansFromDao.size());
        assertTrue(plansFromDao.contains(customMeal0.getId()));
        assertFalse(plansFromDao.contains(customMeal.getId()));
        assertFalse(plansFromDao.contains(customMeal1.getId()));
    }
}