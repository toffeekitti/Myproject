package com.example.kittipob.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.models.UserModel;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.logging.SimpleFormatter;

public class Information_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user);

        DatabaseManager databaseManager = new DatabaseManager(this);
        UserModel data = databaseManager.getUser(1);

        TextView nUser = (TextView) findViewById(R.id.nUser);
        TextView ageUser = (TextView) findViewById(R.id.ageUser);
        TextView wUesr = (TextView) findViewById(R.id.wUser);
        TextView genUser = (TextView) findViewById(R.id.genUser);
        TextView tdiUser = (TextView) findViewById(R.id.overTDI);



        if (data != null){
             nUser.setText(data.getName_user());
            ageUser.setText("" + data.getAge_user());
            wUesr.setText("" + data.getWeight_user());
            genUser.setText(data.getGender_user());

            float calTDI = (float) (data.getWeight_user()*0.8);
            String total = new DecimalFormat("#.##").format(calTDI);

            tdiUser.setText(total);

        }

        Button btn_edit=(Button) findViewById(R.id.btn_edituser);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Information_user.this, Information_user_edit.class);
                startActivity(intent);
                finish();
            }
        });

        Button btn_backhome=(Button) findViewById(R.id.btn_backhome);
        btn_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Information_user.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
