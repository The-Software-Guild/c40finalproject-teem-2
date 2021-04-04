package com.team2.mealPlanner.entities;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private List<CustomMeal> customMeals;
    private List<Integer> favorites;
    private List<Plan> plans;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CustomMeal> getCustomMeals() {
        return customMeals;
    }

    public void setCustomMeals(List<CustomMeal> customMeals) {
        this.customMeals = customMeals;
    }

    public List<Integer> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Integer> favorites) {
        this.favorites = favorites;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userName, user.userName) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(customMeals, user.customMeals) && Objects.equals(favorites, user.favorites) && Objects.equals(plans, user.plans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstName, lastName, password, customMeals, favorites, plans);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
