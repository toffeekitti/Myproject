package com.example.kittipob.myproject.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sAnuphap on 22/10/2558.
 */
public class UserModel extends RealmObject {

    @PrimaryKey
    private int id;

    private String name_user;
    private int age_user;
    private String gender_user;
    private float weight_user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public int getAge_user() {
        return age_user;
    }

    public void setAge_user(int age_user) {
        this.age_user = age_user;
    }

    public String getGender_user() {
        return gender_user;
    }

    public void setGender_user(String gender_user) {
        this.gender_user = gender_user;
    }

    public float getWeight_user() {
        return weight_user;
    }

    public void setWeight_user(float weight_user) {
        this.weight_user = weight_user;
    }
}
