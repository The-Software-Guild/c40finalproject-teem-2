package com.team2.mealPlanner.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CustomMeal {
    private int id;
    private int userId;
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message="Please enter a name.")
    private String name;
    @NotBlank(message = "Ingredients must not be empty.")
    @Size(max = 500, message="Please add in the ingredients.")
    private String ingredients;
    @NotBlank(message = "Note must not be empty.")
    @Size(max = 500, message="Please enter a note.")
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

    @Override
    public String toString() {
        return "CustomMeal{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
