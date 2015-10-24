package com.example.kittipob.myproject.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kittipob on 10/22/15 AD.
 */
public class OrderModel extends RealmObject {

    @PrimaryKey
    private int id;

    private String nFood;
    private String amFood;
    private float totalCat;

    private String report_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnFood() {
        return nFood;
    }

    public void setnFood(String nFood) {
        this.nFood = nFood;
    }

    public String getAmFood() {
        return amFood;
    }

    public void setAmFood(String amFood) {
        this.amFood = amFood;
    }

    public float getTotalCat() {
        return totalCat;
    }

    public void setTotalCat(float totalCat) {
        this.totalCat = totalCat;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }
}
