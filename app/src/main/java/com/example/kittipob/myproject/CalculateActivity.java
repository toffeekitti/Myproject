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
import android.widget.TextView;

import com.example.kittipob.myproject.adapter.OrderAdapter;
import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.models.OrderModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class CalculateActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseManager databaseManager;
    String formattedDate;
    float tdi_user;
    float tdi_calculate;


    TextView valueTDI;
    TextView value_tdi_today;
    TextView value_date;

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
                finish();
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



        // Setup View header
       valueTDI = (TextView) findViewById(R.id.value_over_tdi);
       value_tdi_today = (TextView) findViewById(R.id.value_tdi_today);
        value_date = (TextView) findViewById(R.id.dateToday);

       databaseManager = new DatabaseManager(this);
        if (databaseManager.getUser(1)!=null) {

            // Set TDI USER
            tdi_user = (float) (databaseManager.getUser(1).getWeight_user() * 0.8);

            // Set OVER TDI
            for (int i=0;i < data.size();i++){
               tdi_calculate = tdi_calculate + data.get(i).getTotalCat();
            }


        }else {
            tdi_user = 0.0f;
        }
        valueTDI.setText(""+tdi_user);
        value_tdi_today.setText(""+ tdi_calculate);
        value_date.setText(formattedDate);


        setUpdateReport();

        
    }
    private void refreshData() {
        // Get data
        final List<OrderModel> data = new DatabaseManager(this).getReportId(formattedDate);
        final RecyclerView samples = (RecyclerView) findViewById(R.id.list_order);
        samples.setAdapter(new OrderAdapter(this, data));
        setUpdateReport();

    }

    private void setUpdateReport(){
        boolean status_today;
        if (tdi_user>tdi_calculate){
            status_today=true;
        }else {status_today=false;}


        databaseManager.createOrUpdateReports(formattedDate, tdi_calculate,status_today);
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
