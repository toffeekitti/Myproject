package com.example.kittipob.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kittipob.myproject.adapter.ReportAdapter;
import com.example.kittipob.myproject.adapter.ReportOderAdapter;
import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.models.OrderModel;
import com.example.kittipob.myproject.models.ReportModel;

import java.util.List;

import io.realm.RealmList;

public class ReportDetail extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id_report");

        // Get data
        RealmList<OrderModel> data = new DatabaseManager(this).getReport(id).getOrderReport();
        // Setup RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.list_report_order);
        recyclerView.setAdapter(new ReportOderAdapter(this, data));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_report, menu);
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
