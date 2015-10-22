package com.example.kittipob.myproject.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kittipob.myproject.R;
import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.models.FoodModel;
import com.example.kittipob.myproject.models.OrderModel;

import java.util.List;

/**
 * Created by kittipob on 10/22/15 AD.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private final Context context;
    private final List<OrderModel> data;

    public OrderAdapter(Context context, List<OrderModel> data) {
        this.context = context;
        this.data = data;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order_id;
        private TextView order_nFood;
        private TextView order_amFood;
        private TextView order_total;
        private LinearLayout list;



        public ViewHolder(View itemView) {
            super(itemView);
            list = (LinearLayout) itemView.findViewById(R.id.list_order);
            order_id = (TextView) itemView.findViewById(R.id.order_id);
            order_nFood = (TextView) itemView.findViewById(R.id.order_nFood);
            order_amFood = (TextView) itemView.findViewById(R.id.order_amFood);
            order_total = (TextView) itemView.findViewById(R.id.order_total);




        }
    }

    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context)
        .inflate(R.layout.activity_row_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {
        final OrderModel objOrder = data.get(position);

        holder.order_id.setText(""+position);
        holder.order_nFood.setText(objOrder.getnFood());
        holder.order_amFood.setText(""+objOrder.getAmFood());
        holder.order_total.setText(""+objOrder.getTotalCat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseManager databaseManager = new DatabaseManager(context);
                        databaseManager.deleteOrder(objOrder.getId());
                    }
                });
                dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
