package com.example.kittipob.myproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kittipob.myproject.AddFood;
import com.example.kittipob.myproject.CalculateActivity;
import com.example.kittipob.myproject.R;
import com.example.kittipob.myproject.foodTypes.FoodListTypes;
import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.models.FoodModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kittipob on 10/2/15 AD.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private final Context context;
    private final List<FoodModel> data;


    public FoodAdapter(Context context, List<FoodModel> data) {

        this.context = context;
        this.data = data;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView foodId;
        private TextView foodTdi;
        private Button btn_add;

        public ViewHolder(View itemView) {
            super(itemView);
            foodId = (TextView) itemView.findViewById(R.id.foodName);
            foodTdi = (TextView) itemView.findViewById(R.id.foodTDI);
            btn_add = (Button) itemView.findViewById(R.id.btn_add);


        }
    }
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_row_food, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        final FoodModel objFood = data.get(position);


        holder.foodId.setText(objFood.getFoodName());
        holder.foodTdi.setText("จำนวนแคทเมียมที่ได้รับ\t"+objFood.getFoodTDI()+"\t mg/kg");

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                set OnClick

                View customView = LayoutInflater.from(context).inflate(R.layout.popup_addfood, null);

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);

                dialog.setTitle("เพิ่มอาหาร");
                dialog.setView(customView);

                TextView namefood = (TextView) customView.findViewById(R.id.textView_namefood);
                namefood.setText(objFood.getFoodName());

                TextView cdfood = (TextView) customView.findViewById(R.id.textView_cdfood);
                cdfood.setText(objFood.getFoodTDI() + "");

                TextView weightfood = (TextView) customView.findViewById(R.id.textView_weightfood);
                weightfood.setText(objFood.getFoodWeight());

                TextView unitfood = (TextView) customView.findViewById(R.id.textView_unitfood);
                unitfood.setText(objFood.getFoodUnit());

                final EditText getAmount = (EditText) customView.findViewById(R.id.input_num);


                Button btn_add = (Button) customView.findViewById(R.id.btn_add);
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseManager databaseManager = new DatabaseManager(context);

                        String x = getAmount.getText().toString();
                        int valueAmount = Integer.parseInt(x);

                        float total_tdi = (float) (valueAmount * objFood.getFoodTDI());
                        //  get date
                        Calendar c = Calendar.getInstance();
                        String formattedDate =  new SimpleDateFormat("dd-MM-yyyy").format(c.getTime());
                        Intent intent = new Intent(context,CalculateActivity.class);
//                        Toast.makeText(context,objFood.getFoodName()+"\t"+valueAmount+"\t หน่วย",Toast.LENGTH_LONG).show();
                        databaseManager.createOrUpdateOrders(objFood.getId()+1,objFood.getFoodName(),valueAmount,total_tdi,formattedDate);


                        ((Activity) context).startActivityForResult(intent, CalculateActivity.rg_update);
                        ((Activity) context).finish();
                    }
                });



                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}
