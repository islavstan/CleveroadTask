package com.islavstan.cleveroadtask.loader;

import android.content.Context;
import android.util.Log;

import com.islavstan.cleveroadtask.api.ApiConstant;
import com.islavstan.cleveroadtask.model.Queries;
import com.islavstan.cleveroadtask.point.GetDataPoint;




public class QueriesLoader extends RetrofitLoader<Queries, GetDataPoint> {
    private String q;

    public QueriesLoader(Context context, GetDataPoint service, String q) {
        super(context, service);
        this.q = q;
    }

    @Override
    public Queries call(GetDataPoint service) {
        return service.getData(ApiConstant.key, ApiConstant.cx, q);

    }
}
