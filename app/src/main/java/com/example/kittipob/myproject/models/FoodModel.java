package com.example.kittipob.myproject.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kittipob on 10/2/15 AD.
 */
public class FoodModel extends RealmObject {

    @PrimaryKey
    private int id;

    private String foodName;
    private double foodTDI;
    private String foodType;
    private String foodUnit;
    private String foodWeight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodTDI() {
        return foodTDI;
    }

    public void setFoodTDI(double foodTDI) {
        this.foodTDI = foodTDI;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodUnit() {
        return foodUnit;
    }

    public void setFoodUnit(String foodUnit) {
        this.foodUnit = foodUnit;
    }

    public String getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(String foodWeight) {
        this.foodWeight = foodWeight;
    }
}
