package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.MealType;
import com.team2.mealPlanner.entities.PlanMeal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanMealDBTest {

    @Autowired
    MealTypeDao mealTypeDao;

    @Autowired
    PlanMealDao planMealDao;

    @Autowired
    CustomMealDao customMealDao;

    @Autowired
    PlanDao planDao;

    @Autowired
    UserDao userDao;

    public PlanMealDBTest(){

    }

    @Before
    public void setUp(){
        List<PlanMeal> planMeals = planMealDao.getAllPlanMeals();
        for(PlanMeal planMeal : planMeals){
            planMealDao.deletePlanMeal(planMeal.getId());
        }

       List<MealType> mealTypes = mealTypeDao.getAllMealTypes();
       for(MealType mealType : mealTypes) {
          mealTypeDao.deleteMealType(mealType.getId());
       }

    }

    @Test
    public void testAddAndGetPlanMeal(){
        MealType mealType = new MealType();
        mealType.setId(1);
        mealType.setName("Breakfast Test");

        mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setPlanId(1);
        planMeal.setMealTypeId(mealType.getId());
        planMeal.setCustom(false);
        planMeal.setMealId(1);
        planMeal.setMealType(mealType);

        planMealDao.addPlanMeal(planMeal);

        PlanMeal fromDao = planMealDao.getPlanMealById(planMeal.getId());

        assertEquals(fromDao, planMeal);

    }

    @Test
    public void testGetAllPlanMeal(){
        MealType mealType = new MealType();
        mealType.setId(1);
        mealType.setName("Breakfast Test");

        mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setPlanId(1);
        planMeal.setMealTypeId(mealType.getId());
        planMeal.setCustom(false);
        planMeal.setMealId(1);
        planMeal.setMealType(mealType);

        planMealDao.addPlanMeal(planMeal);

        PlanMeal planMeal2 = new PlanMeal();
        planMeal2.setPlanId(1);
        planMeal2.setMealTypeId(mealType.getId());
        planMeal2.setCustom(false);
        planMeal2.setMealId(5);
        planMeal2.setMealType(mealType);

        planMealDao.addPlanMeal(planMeal2);

        List<PlanMeal> planMeals = planMealDao.getAllPlanMeals();

        assertEquals(2,planMeals.size());
        assertTrue(planMeals.contains(planMeal));
        assertTrue(planMeals.contains(planMeal2));

    }

    @Test
    public void testUpdateMealPlan(){
        MealType mealType = new MealType();
        mealType.setId(1);
        mealType.setName("Breakfast Test");

        mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setPlanId(1);
        planMeal.setMealTypeId(mealType.getId());
        planMeal.setCustom(false);
        planMeal.setMealId(1);
        planMeal.setMealType(mealType);

        planMealDao.addPlanMeal(planMeal);

        PlanMeal fromDao = planMealDao.getPlanMealById(planMeal.getId());

        assertEquals(fromDao, planMeal);

        planMeal.setMealId(55);
        planMealDao.updatePlanMeal(planMeal);

        assertNotEquals(planMeal, fromDao);

        fromDao = planMealDao.getPlanMealById(planMeal.getId());

        assertEquals(planMeal, fromDao);
    }

    @Test
    public void testDeleteMealPlan(){
        MealType mealType = new MealType();
        mealType.setId(1);
        mealType.setName("Breakfast Test");

        mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setPlanId(1);
        planMeal.setMealTypeId(mealType.getId());
        planMeal.setCustom(false);
        planMeal.setMealId(1);
        planMeal.setMealType(mealType);

        planMealDao.addPlanMeal(planMeal);

        PlanMeal fromDao = planMealDao.getPlanMealById(planMeal.getId());

        assertEquals(fromDao, planMeal);

        boolean isDeleted = planMealDao.deletePlanMeal(planMeal.getId());

        assertTrue(isDeleted);

        fromDao = planMealDao.getPlanMealById(planMeal.getId());

        assertNull(fromDao);
    }

    @Test
    public void getPlanMealsByPlanId(){
        MealType mealType = new MealType();
        mealType.setId(1);
        mealType.setName("Breakfast Test");

        mealTypeDao.addMealType(mealType);

        PlanMeal planMeal = new PlanMeal();
        planMeal.setPlanId(1);
        planMeal.setMealTypeId(mealType.getId());
        planMeal.setCustom(false);
        planMeal.setMealId(1);
        planMeal.setMealType(mealType);

        planMealDao.addPlanMeal(planMeal);

        PlanMeal planMeal2 = new PlanMeal();
        planMeal2.setPlanId(1);
        planMeal2.setMealTypeId(mealType.getId());
        planMeal2.setCustom(false);
        planMeal2.setMealId(5);
        planMeal2.setMealType(mealType);

        planMealDao.addPlanMeal(planMeal2);

        PlanMeal planMeal3 = new PlanMeal();
        planMeal3.setPlanId(2);
        planMeal3.setMealTypeId(mealType.getId());
        planMeal3.setCustom(false);
        planMeal3.setMealId(590);
        planMeal3.setMealType(mealType);

        planMealDao.addPlanMeal(planMeal3);

        List<PlanMeal> planMeals = planMealDao.getAllPlanMealsByPlanId(1);

        assertEquals(2,planMeals.size());
        assertTrue(planMeals.contains(planMeal));
        assertTrue(planMeals.contains(planMeal2));
    }
}