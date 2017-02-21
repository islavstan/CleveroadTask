package com.islavstan.cleveroadtask.interactor;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.api.ApiClient;
import com.islavstan.cleveroadtask.api.ApiConstant;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.model.Queries;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.point.GetDataPoint;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Random;

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

    @Override
    public void saveToDB(QueriesData data, DBMethods db, Picasso picasso, ResultFinishedListener listener) {
        final String photoPath = randomName();
        String src = data.getPagemap().getCseThumbnailData().get(0).getSrc();
        picasso.load(src).into(new com.squareup.picasso.Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                String root = Environment.getExternalStorageDirectory().toString();
                File dir = new File(root + "/CleveroadTask/images/");
                if (!dir.exists()) dir.mkdirs();
                File fTo = new File(root + "/CleveroadTask/images/" + photoPath);
                try {
                    FileOutputStream ostream = new FileOutputStream(fTo);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream);
                    ostream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        db.saveContent(data.getTitle(), photoPath);
        listener.onSuccessSaveToDB();


    }

    @Override
    public void deleteFromDb(QueriesData data, DBMethods db, ResultFinishedListener listener) {
        db.delete(data.getId());
        listener.onSuccessDeleteFromDB();

    }

    public String randomName() {
        Random r = new Random(); // just create one and keep it around
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        final int N = 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        String randomName = sb.toString();

        return randomName + ".jpeg";
    }


}
