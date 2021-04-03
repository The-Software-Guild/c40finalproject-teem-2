package com.team2.mealPlanner.entities;

import java.util.Objects;

public class PlanMeal {
    private int id;
    private int mealId;
    private boolean isCustom;
    private MealType mealType;

    public PlanMeal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanMeal planMeal = (PlanMeal) o;
        return id == planMeal.id && isCustom == planMeal.isCustom && Objects.equals(mealType, planMeal.mealType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isCustom, mealType);
    }
}
