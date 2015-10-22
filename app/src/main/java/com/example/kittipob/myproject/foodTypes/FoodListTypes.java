package com.example.kittipob.myproject.foodTypes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kittipob.myproject.MainActivity;
import com.example.kittipob.myproject.R;
import com.example.kittipob.myproject.adapter.FoodAdapter;
import com.example.kittipob.myproject.manager.DatabaseManager;

import com.example.kittipob.myproject.models.FoodModel;

import java.util.List;


public class FoodListTypes extends AppCompatActivity {
    public final static int rg_update = 1;


    RecyclerView recyclerView;

    Intent intent;
    String nameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type_food);

        intent = getIntent();
        nameType = intent.getStringExtra("type");

        // Get data
        List<FoodModel> data = new DatabaseManager(this).getType(nameType);

        // Setup RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.list_fruit);
        recyclerView.setAdapter(new FoodAdapter(this, data));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //web sw




    }

    private void refreshData() {
        // Get data
        final List<FoodModel> data = new DatabaseManager(this).getType(nameType);
        final RecyclerView samples = (RecyclerView) findViewById(R.id.list_fruit);
        samples.setAdapter(new FoodAdapter(this, data));



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == rg_update) {

            finish();

        }

    }


}
