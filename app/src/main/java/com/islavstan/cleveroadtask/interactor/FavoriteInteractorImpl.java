package com.islavstan.cleveroadtask.interactor;

import android.util.Log;

import com.islavstan.cleveroadtask.adapters.MyFavoriteRecAdapter;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.model.QueriesData;

import java.util.List;


public class FavoriteInteractorImpl implements FavoriteInteractor {

    @Override
    public void loadData(MyFavoriteRecAdapter adapter, String searchRequest, DBMethods db) {
        List<QueriesData> list = db.getContentFromDB(0);
        adapter.loadData(list);

    }

    @Override
    public void addData(MyFavoriteRecAdapter adapter, DBMethods db, int index) {
        List<QueriesData> list = db.getContentFromDB(index);
        if (list.size() == 0) {
            adapter.setNoMoreItem();
        } else
            adapter.addMore(list);
    }


    @Override
    public void deleteFromDb(int id, DBMethods db, FavoriteFinishedListener listener, int position, MyFavoriteRecAdapter adapter) {
        Log.d("stas", id+" delete id" );
        db.delete(id);
        adapter.deleteItem(position);

    }
}
