package com.team2.mealPlanner.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Plan {
    private int id;
    private int idUser;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return id == plan.id && idUser == plan.idUser && Objects.equals(date, plan.date) && Objects.equals(planMeals, plan.planMeals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, date, planMeals);
    }
}
