package com.example.kittipob.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.manager.WebServiceCallbackListener;
import com.example.kittipob.myproject.manager.WebServiceManager;
import com.example.kittipob.myproject.models.ReportModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements WebServiceCallbackListener {
    ProgressDialog progressDialog;
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        ImageView imguser = (ImageView) findViewById(R.id.imageView_informationUser);
        imguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,information_user_list.class);

                startActivity(intent);
            }
        });*/
        LinearLayout box_user = (LinearLayout) findViewById(R.id.LinearLayout_infoUser);
        box_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Information_user.class);
                startActivity(intent);

            }
        });

        LinearLayout box_cal = (LinearLayout) findViewById(R.id.LinearLayout_calculate);
        box_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CalculateActivity.class);

                startActivity(intent);

            }
        });

        LinearLayout box_report = (LinearLayout) findViewById(R.id.LinearLayout_report);
        box_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);

                startActivity(intent);

            }
        });

        LinearLayout box_infor = (LinearLayout) findViewById(R.id.LinearLayout_information);
        box_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,information.class);

                startActivity(intent);

            }
        });

            progressDialog = ProgressDialog.show(this, null, "loading...", true, false);
            new WebServiceManager(this,this).requestFood();


        //  get date
        Calendar c = Calendar.getInstance();
        String formattedDate =  new SimpleDateFormat("dd-MM-yyyy").format(c.getTime());

        // Create Report of to day.
        DatabaseManager databaseManager = new DatabaseManager(this);
        ReportModel report = databaseManager.getReport(formattedDate);

        if (report == null){
            databaseManager.createOrUpdateReports(formattedDate,0.0f,false);
            Toast.makeText(this,"Create id report to day success!",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onWebServiceFailed() {

        Toast.makeText(this, "FAIL", Toast.LENGTH_LONG).show();
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                progressDialog.dismiss();
            }
        };
        handler.postDelayed(r, 1000);



    }
    @Override
    public void onWebServiceCallback() {

        progressDialog.dismiss();

    }


}
