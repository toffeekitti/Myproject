package com.example.kittipob.myproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.kittipob.myproject.R;
import com.example.kittipob.myproject.ReportActivity;
import com.example.kittipob.myproject.ReportDetail;
import com.example.kittipob.myproject.models.FoodModel;
import com.example.kittipob.myproject.models.ReportModel;

import java.util.List;

/**
 * Created by sAnuphap on 24/10/2558.
 */
public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private final Context context;
    private final List<ReportModel> data;

    public ReportAdapter(Context context, List<ReportModel> data) {
        this.context = context;
        this.data = data;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView report_id;
        private TextView report_totalTDI;
        private TextView report_status;

        public ViewHolder(View itemView) {
            super(itemView);
            report_id = (TextView) itemView.findViewById(R.id.report_id);
            report_totalTDI = (TextView) itemView.findViewById(R.id.report_tdi);
            report_status = (TextView) itemView.findViewById(R.id.report_status);



        }
    }


    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context)
                .inflate(R.layout.row_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportAdapter.ViewHolder holder, int position) {
        final ReportModel objReport = data.get(position);

        holder.report_id.setText(objReport.getId());
        holder.report_totalTDI.setText(""+objReport.getTotal_tdi());
        holder.report_status.setText(""+objReport.isStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReportDetail.class);
                intent.putExtra("id_report", objReport.getId());
                ((Activity) context).startActivityForResult(intent, ReportActivity.rg_update);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
