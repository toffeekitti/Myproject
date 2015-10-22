package com.example.kittipob.myproject.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kittipob on 10/2/15 AD.
 */
public class WebServiceManager {

    private final static String FOOD_URL =  "http://192.168.1.13:8888/api/food";

    final Context context;
    final WebServiceCallbackListener listener;


    public WebServiceManager(Context context, WebServiceCallbackListener listener) {
        super();
        this.context = context;
        this.listener = listener;
    }



    public void requestFood() {

        String url = FOOD_URL;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("WebServiceManager", "Response: " + response.toString());

                        DatabaseManager databaseManager = new DatabaseManager(context);

                        try {

                            JSONArray foodsRaw = response.getJSONArray("data");
                            for (int i = 0; i < foodsRaw.length(); i++) {



                                JSONObject foodRaw = foodsRaw.getJSONObject(i);
                                int id = foodRaw.getInt("id_food");
                                String foodName = foodRaw.getString("name_food");
                                double foodTDI = foodRaw.getDouble("cd_food");
                                String foodType = foodRaw.getString("type_food");
                                String foodAmount = foodRaw.getString("unit_food");
                                String foodWeight = foodRaw.getString("weight_food");


                                databaseManager.createOrUpdateFoods(id, foodName, foodTDI, foodType, foodAmount,foodWeight);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listener.onWebServiceCallback();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onWebServiceFailed();
                    }

            });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }




}

