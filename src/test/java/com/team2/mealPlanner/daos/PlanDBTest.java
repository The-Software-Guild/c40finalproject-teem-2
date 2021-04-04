package com.team2.mealPlanner.daos;

import com.team2.mealPlanner.entities.Plan;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlanDBTest {
    @Autowired
    PlanDao planDao;


    @BeforeEach
    void setUp() {
        List<Plan> plans = planDao.getAllPlans();
        for ( Plan plan: plans) {
             planDao.deletePlan(plan.getId());
        }
    }

    @Test
    void getPlanById() {

    }

    @Test
    void getAllPlans() {
        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan = planDao.addPlan(plan);

        Plan plan2 = new Plan();
        plan2.setDate(LocalDate.now());
        plan2 = planDao.addPlan(plan2);



        List<Plan> plans = planDao.getAllPlans();

        assertEquals(2, plans.size());
        assertTrue( plan.equals(plans.get(0))  || plan.equals(plans.get(1)) );
        assertTrue( plan2.equals(plans.get(0)) || plan2.equals(plans.get(1)));
    }

    @Test
    void addPlan() {
        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan = planDao.addPlan(plan);

        Plan planFromDb = planDao.getPlanById(plan.getId());
        assertTrue(plan.equals(planFromDb));
    }

    @Test
    void updatePlan() {
        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan = planDao.addPlan(plan);

        Plan planFromDb = planDao.getPlanById(plan.getId());

        assertTrue(plan.equals(planFromDb));

        plan.setDate(LocalDate.now());
        boolean status = planDao.updatePlan(plan);
        assertEquals(true, status);

        planFromDb = planDao.getPlanById( plan.getId());

        assertTrue( plan.equals(planFromDb) );
    }

    @Test
    void deletePlan() {
        Plan plan = new Plan();
        plan.setDate(LocalDate.now());
        plan = planDao.addPlan(plan);

        planDao.deletePlan(plan.getId());
        List<Plan> plans = planDao.getAllPlans();
        assertFalse( plans.contains(plan) );

    }
}