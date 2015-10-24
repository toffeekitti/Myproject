package com.example.kittipob.myproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kittipob.myproject.CalculateActivity;
import com.example.kittipob.myproject.R;
import com.example.kittipob.myproject.ReportActivity;
import com.example.kittipob.myproject.ReportDetail;
import com.example.kittipob.myproject.manager.DatabaseManager;
import com.example.kittipob.myproject.models.OrderModel;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by kittipob on 10/22/15 AD.
 */
public class ReportOderAdapter extends RecyclerView.Adapter<ReportOderAdapter.ViewHolder> {
    private final Context context;
    private final RealmList<OrderModel> data;

    public ReportOderAdapter(Context context, RealmList<OrderModel> data) {
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
    public ReportOderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context)
        .inflate(R.layout.activity_row_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportOderAdapter.ViewHolder holder, int position) {
        final OrderModel objOrder = data.get(position);

        holder.order_id.setText(""+(position+1));
        holder.order_nFood.setText(objOrder.getnFood());
        holder.order_amFood.setText(""+objOrder.getAmFood());
        holder.order_total.setText("" + objOrder.getTotalCat());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
