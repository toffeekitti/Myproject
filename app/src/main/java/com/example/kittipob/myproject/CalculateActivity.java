package com.example.kittipob.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kittipob.myproject.adapter.FoodAdapter;
import com.example.kittipob.myproject.adapter.OrderAdapter;
import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.models.FoodModel;
import com.example.kittipob.myproject.models.OrderModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class CalculateActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    String formattedDate;
   public static int rg_update=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btn_addfood = (Button) findViewById(R.id.addfood);
        btn_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateActivity.this, AddFood.class);

                startActivity(intent);
            }
        });
        //  get date
        Calendar c = Calendar.getInstance();
        formattedDate =  new SimpleDateFormat("dd-MM-yyyy").format(c.getTime());

        // Get data
        List<OrderModel> data = new DatabaseManager(this).getReportId(formattedDate);

        // Setup RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.list_order);
        recyclerView.setAdapter(new OrderAdapter(this, data));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
    }
    private void refreshData() {
        // Get data
        final List<OrderModel> data = new DatabaseManager(this).getReportId(formattedDate);
        final RecyclerView samples = (RecyclerView) findViewById(R.id.list_order);
        samples.setAdapter(new OrderAdapter(this, data));



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == rg_update) {
            Intent intent = new Intent(this,CalculateActivity.class);
            refreshData();
            startActivity(intent);
            finish();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculate, menu);
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
        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
