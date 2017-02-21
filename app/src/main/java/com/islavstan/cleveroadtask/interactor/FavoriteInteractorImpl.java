package com.islavstan.cleveroadtask.interactor;

import com.islavstan.cleveroadtask.adapters.MyFavoriteRecAdapter;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.model.QueriesData;

import java.util.List;


public class FavoriteInteractorImpl implements FavoriteInteractor {

    @Override
    public void loadData(MyFavoriteRecAdapter adapter, String searchRequest, DBMethods db) {
        List<QueriesData> list = db.getContentFromDB();
        adapter.loadData(list);

    }

    @Override
    public void deleteFromDb(QueriesData data, DBMethods db, FavoriteFinishedListener listener) {
        db.delete(data.getId());

    }
}
