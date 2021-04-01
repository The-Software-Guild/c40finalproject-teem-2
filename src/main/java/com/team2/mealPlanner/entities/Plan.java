package com.team2.mealPlanner.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Plan {
    private int id;
    private LocalDate date;
    private List<PlanMeal> planMeals;

    public Plan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<PlanMeal> getPlanMeals() {
        return planMeals;
    }

    public void setPlanMeals(List<PlanMeal> planMeals) {
        this.planMeals = planMeals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return id == plan.id && Objects.equals(date, plan.date) && Objects.equals(planMeals, plan.planMeals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, planMeals);
    }
}
