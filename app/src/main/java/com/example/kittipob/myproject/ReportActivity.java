package com.example.kittipob.myproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kittipob.myproject.adapter.OrderAdapter;
import com.example.kittipob.myproject.adapter.ReportAdapter;
import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.models.OrderModel;
import com.example.kittipob.myproject.models.ReportModel;

import java.util.List;


public class ReportActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public final static int rg_update = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get data
        List<ReportModel> data = new DatabaseManager(this).getReports();

        // Setup RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.list_report);
        recyclerView.setAdapter(new ReportAdapter(this, data));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ) {

        if (requestCode == rg_update) {

            Intent intent = new Intent(ReportActivity.this,ReportActivity.class);
            refreshData();
            startActivity(intent);
            finish();
        }
    }

    private void refreshData() {

        // Get data
        List<ReportModel> data = new DatabaseManager(this).getReports();

        // Setup RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.list_report);
        recyclerView.setAdapter(new ReportAdapter(this, data));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
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
