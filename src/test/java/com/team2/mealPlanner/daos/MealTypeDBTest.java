package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.MealType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealTypeDBTest {

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

    public MealTypeDBTest(){

    }

    @Before
    public void setUp(){
        List<MealType> mealTypes = mealTypeDao.getAllMealTypes();
        for(MealType mealType : mealTypes) {
            mealTypeDao.deleteMealType(mealType.getId());
        }
    }

    @Test
    public void testAddAndGetMealType(){
        MealType mealType = new MealType();
        mealType.setId(1);
        mealType.setName("Breakfast Test");

        mealTypeDao.addMealType(mealType);

        MealType fromDao = mealTypeDao.getMealTypeById(mealType.getId());

        assertEquals(mealType, fromDao);
    }

    @Test
    public void testGetAllMealTypes(){

        MealType mealType = new MealType();
        mealType.setId(1);
        mealType.setName("Breakfast Test");

        mealTypeDao.addMealType(mealType);


        MealType mealType2 = new MealType();
        mealType2.setId(2);
        mealType2.setName("Lunch Test");

        mealTypeDao.addMealType(mealType2);

        MealType mealType3 = new MealType();
        mealType3.setId(3);
        mealType3.setName("Dinner Test");

        mealTypeDao.addMealType(mealType3);

        List<MealType> mealTypes = mealTypeDao.getAllMealTypes();

        assertEquals(3,mealTypes.size());
        assertTrue(mealTypes.contains(mealType));
        assertTrue(mealTypes.contains(mealType2));
        assertTrue(mealTypes.contains(mealType3));
    }

    @Test
    public void testUpdateMealTypes(){
        MealType mealType = new MealType();
        mealType.setId(1);
        mealType.setName("Breakfast Test");

        mealTypeDao.addMealType(mealType);

        MealType fromDao = mealTypeDao.getMealTypeById(mealType.getId());

        assertEquals(mealType, fromDao);

        mealType.setName("Updated Name");
        mealTypeDao.updateMealType(mealType);

        assertNotEquals(mealType, fromDao);

        fromDao = mealTypeDao.getMealTypeById(mealType.getId());

        assertEquals(fromDao, mealType);
    }

    @Test
    public void testDeleteMealTypes(){
        MealType mealType = new MealType();
        mealType.setId(1);
        mealType.setName("Breakfast Test");

        mealTypeDao.addMealType(mealType);

        MealType fromDao = mealTypeDao.getMealTypeById(mealType.getId());

        assertEquals(mealType, fromDao);

        boolean isDeleted = mealTypeDao.deleteMealType(mealType.getId());

        assertTrue(isDeleted);

        fromDao = mealTypeDao.getMealTypeById(mealType.getId());

        assertNull(fromDao);
    }
}