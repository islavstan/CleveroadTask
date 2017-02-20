package com.islavstan.cleveroadtask.interactor;

import android.util.Log;

import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.api.ApiClient;
import com.islavstan.cleveroadtask.api.ApiConstant;
import com.islavstan.cleveroadtask.model.Queries;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.point.GetDataPoint;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResultInteractorImpl implements ResultInteractor {

    @Override
    public void loadData(final MyRecyclerViewAdapter adapter, String searchRequest) {
        if (searchRequest.equals("")) {

        } else {
            final GetDataPoint point = ApiClient.getRetrofit().create(GetDataPoint.class);
            Call<Queries> call = point.getData(ApiConstant.key, ApiConstant.cx, searchRequest);
            call.enqueue(new Callback<Queries>() {
                @Override
                public void onResponse(Call<Queries> call, Response<Queries> response) {
                    if (response.isSuccessful()) {
                        Queries queries = response.body();
                        List<QueriesData> queriesDatas = queries.getQueriesDataList();
                        if (queriesDatas != null)
                            adapter.loadData(queriesDatas);


                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Log.d("stas", "GetDataPoint error = " + jObjError.getString("error"));

                        } catch (Exception e) {
                            Log.d("stas", e.getMessage());
                        }

                    }

                }

                @Override
                public void onFailure(Call<Queries> call, Throwable t) {
                    Log.d("stas", "false");
                }


            });
        }
    }
}
