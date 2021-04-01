package com.team2.mealPlanner.entities;

import java.util.Objects;

public class MealType {

    private int id;
    private String name;

    public MealType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealType mealType = (MealType) o;
        return id == mealType.id && Objects.equals(name, mealType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
