package com.example.kittipob.myproject.manager;

import android.content.Context;

import com.example.kittipob.myproject.models.FoodModel;
import com.example.kittipob.myproject.models.OrderModel;

import java.util.List;

import io.realm.Realm;
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


    public void createOrUpdateFoods(int id,String foodName,double foodTDI,String foodType,String foodAmount,String foodWieght) {
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
    public void createOrUpdateOrders(int id,String nFood,int amFood,float totalCat,String report_id) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        OrderModel order = getOrderFood(id);

        if (order == null) {
            order = realm.createObject(OrderModel.class);
        }

        order.setId(id);
        order.setnFood(nFood);
        order.setAmFood(amFood);
        order.setTotalCat(totalCat);
        order.setReport_id(report_id);

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
}
