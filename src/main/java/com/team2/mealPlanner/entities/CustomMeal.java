package com.team2.mealPlanner.entities;

import java.util.Objects;

public class CustomMeal {
    private int id;
    private int userId;
    private String name;
    private String ingredients;
    private String note;

    public CustomMeal() {

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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomMeal that = (CustomMeal) o;
        return id == that.id && userId == that.userId && Objects.equals(name, that.name) && Objects.equals(ingredients, that.ingredients) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, ingredients, note);
    }
}
