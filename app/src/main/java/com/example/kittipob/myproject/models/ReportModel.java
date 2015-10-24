package com.example.kittipob.myproject.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sAnuphap on 22/10/2558.
 */
public class ReportModel extends RealmObject {

    @PrimaryKey
    private String id ;
    private float total_tdi;
    private boolean status;

    private RealmList<OrderModel> orderReport;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getTotal_tdi() {
        return total_tdi;
    }

    public void setTotal_tdi(float total_tdi) {
        this.total_tdi = total_tdi;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public RealmList<OrderModel> getOrderReport() {
        return orderReport;
    }

    public void setOrderReport(RealmList<OrderModel> orderReport) {
        this.orderReport = orderReport;
    }
}
