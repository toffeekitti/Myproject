package com.example.kittipob.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class information_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user);

        Button btn_edit=(Button) findViewById(R.id.btn_edituser);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(information_user.this, information_user_edit.class);
                startActivity(intent);
            }
        });

        Button btn_backhome=(Button) findViewById(R.id.btn_backhome);
        btn_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(information_user.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
