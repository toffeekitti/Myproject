package com.example.kittipob.myproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kittipob.myproject.foodTypes.FoodListTypes;



public class AddFood extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Button btnstarch = (Button) findViewById(R.id.buttonstarch);
        btnstarch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddFood.this,FoodListTypes.class);
                intent.putExtra("type","Starch");

                startActivity(intent);
                finish();
            }
        });

        Button btnvegetable = (Button) findViewById(R.id.buttonvegetable);
        btnvegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddFood.this,FoodListTypes.class);
                intent.putExtra("type","Vegetable");
                startActivity(intent);
                finish();
            }
        });

        Button btnmeet = (Button) findViewById(R.id.buttonmeet);
        btnmeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddFood.this,FoodListTypes.class);
                intent.putExtra("type","Meet");
                startActivity(intent);
                finish();
            }
        });

        Button btnfruit = (Button) findViewById(R.id.buttonfruit);
        btnfruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddFood.this,FoodListTypes.class);
                intent.putExtra("type","Fruit");

                startActivity(intent);
                finish();
            }
        });

        Button btnprocessed = (Button) findViewById(R.id.buttonprocessed);
        btnprocessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddFood.this,FoodListTypes.class);
                intent.putExtra("type","Processed Foods");
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
