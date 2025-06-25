package com.example.demo.model;

public class CompareRequest {
    private String userCountry;
    private String userState;
    private String dishName;
    private String dishCountry;

    // Getters and setters
    public String getUserCountry() { return userCountry; }
    public void setUserCountry(String userCountry) { this.userCountry = userCountry; }

    public String getUserState() { return userState; }
    public void setUserState(String userState) { this.userState = userState; }

    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }

    public String getDishCountry() { return dishCountry; }
    public void setDishCountry(String dishCountry) { this.dishCountry = dishCountry; }
}
