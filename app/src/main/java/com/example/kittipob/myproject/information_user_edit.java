package com.example.kittipob.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.models.UserModel;

public class Information_user_edit extends AppCompatActivity {

    String gender;
    String name;
    int age;
    float weight;

    EditText ed_name ;
    EditText ed_age;
    EditText ed_weight ;


    DatabaseManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user_edit);

        data = new DatabaseManager(Information_user_edit.this);
        UserModel user = data.getUser(1);

        RadioButton rb_male = (RadioButton) findViewById(R.id.male);
        RadioButton rb_female = (RadioButton) findViewById(R.id.female);
        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_age = (EditText) findViewById(R.id.ed_age);
        ed_weight = (EditText) findViewById(R.id.ed_weight);

        ed_name.setText(user.getName_user());
        ed_age.setText(""+user.getAge_user());
        ed_weight.setText(""+user.getWeight_user());
        




        Button btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = ed_name.getText().toString();
                String x = ed_age.getText().toString();
                age = Integer.parseInt(x);
                String w = ed_weight.getText().toString();
                weight = Float.parseFloat(w);



                Intent intent = new Intent(Information_user_edit.this,Information_user.class);

                data.createOrUpdateUser(1, name, age, gender, weight);
                startActivity(intent);
                finish();
            }
        });



        Button btn_cancle=(Button) findViewById(R.id.btn_cancel);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Information_user_edit.this, Information_user.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.male:
                if (checked)
                    gender = "ชาย";
                break;
            case R.id.female:
                if (checked)
                    gender = "หญิง";
                break;
        }
    }


}
