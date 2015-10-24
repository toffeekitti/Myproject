package com.example.kittipob.myproject.manager;

import android.content.Context;

import com.example.kittipob.myproject.models.FoodModel;
import com.example.kittipob.myproject.models.OrderModel;
import com.example.kittipob.myproject.models.ReportModel;
import com.example.kittipob.myproject.models.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by kittipob on 10/2/15 AD.
 */
public class DatabaseManager  {

    private final Context context;


    public DatabaseManager(final Context context) {
        this.context = context;
    }
    public List<FoodModel> getFoods() {
        return Realm.getInstance(context)
                .where(FoodModel.class)
                .findAll();
    }

    public FoodModel getFood(final int foodId) {
        return Realm.getInstance(context)
                .where(FoodModel.class)
                .equalTo("id", foodId)
                .findFirst();
    }
    public RealmResults<FoodModel> getType(final String foodType) {
        return Realm.getInstance(context)
                .where(FoodModel.class)
                .contains("foodType", foodType)
                .findAll();
    }


    public void createOrUpdateFoods(int id,String foodName,double foodTDI,String foodType,String foodAmount,double foodWieght) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        FoodModel food = getFood(id);

        if (food == null) {
            food = realm.createObject(FoodModel.class);

        }

        food.setId(id);
        food.setFoodName(foodName);
        food.setFoodTDI(foodTDI);
        food.setFoodType(foodType);
        food.setFoodUnit(foodAmount);
        food.setFoodWeight(foodWieght);




        realm.commitTransaction();

    }

    public List<OrderModel> getOderFoods() {
        return Realm.getInstance(context)
                .where(OrderModel.class)
                .findAll();
    }

    public OrderModel getOrderFood(final int oderId) {
        return Realm.getInstance(context)
                .where(OrderModel.class)
                .equalTo("id", oderId)
                .findFirst();
    }
    public RealmResults<OrderModel> getReportId(final String report_id) {
        return Realm.getInstance(context)
                .where(OrderModel.class)
                .contains("report_id", report_id)
                .findAll();
    }


    public List<UserModel> getUsers() {
        return Realm.getInstance(context)
                .where(UserModel.class)
                .findAll();
    }

    public UserModel getUser(final int id) {
        return Realm.getInstance(context)
                .where(UserModel.class)
                .equalTo("id", id)
                .findFirst();
    }

    public void createOrUpdateUser(int id,String name,int age,String gender,float weight) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        UserModel user = getUser(id);

        if (user == null) {
            user = realm.createObject(UserModel.class);
        }

        user.setId(id);
        user.setName_user(name);
        user.setAge_user(age);
        user.setGender_user(gender);
        user.setWeight_user(weight);

        realm.commitTransaction();

    }

    public void deleteOrder(int id){
        Realm realm = Realm.getInstance(context);
        OrderModel toDelete = realm.where(OrderModel.class)
                .equalTo("id", id).findFirst();
        realm.beginTransaction();
        toDelete.removeFromRealm();
        realm.commitTransaction();


    }

    public ReportModel getReport(final String report_id) {
        return Realm.getInstance(context)
                .where(ReportModel.class)
                .equalTo("id", report_id)
                .findFirst();
    }
    public List<ReportModel> getReports() {
        return Realm.getInstance(context)
                .where(ReportModel.class)
                .findAll();
    }


    public void createOrUpdateReports(String id,float total_tdi,boolean status) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        ReportModel report = getReport(id);

        if (report == null) {
            report = realm.createObject(ReportModel.class);

        }

        report.setId(id);
        report.setTotal_tdi(total_tdi);
        report.setStatus(status);




        realm.commitTransaction();

    }
    public void createOrUpdateOrders(int id,String nFood,String amFood,float totalCat,String report_id) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        OrderModel order = getOrderFood(id);

        if (order == null) {
            order = realm.createObject(OrderModel.class);
            ReportModel report = getReport(report_id);
            report.getOrderReport().add(order);
        }

        order.setId(id);
        order.setnFood(nFood);
        order.setAmFood(amFood);
        order.setTotalCat(totalCat);
        order.setReport_id(report_id);

        realm.commitTransaction();

    }

}
