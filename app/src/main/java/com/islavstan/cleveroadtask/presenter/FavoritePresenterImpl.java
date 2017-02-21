package com.islavstan.cleveroadtask.presenter;


import com.islavstan.cleveroadtask.adapters.MyFavoriteRecAdapter;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.interactor.FavoriteInteractor;
import com.islavstan.cleveroadtask.interactor.FavoriteInteractorImpl;
import com.islavstan.cleveroadtask.interactor.ResultInteractor;
import com.islavstan.cleveroadtask.interactor.ResultInteractorImpl;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.view.FragmentView;

public class FavoritePresenterImpl implements FavoritePresenter, FavoriteInteractor.FavoriteFinishedListener {
    private FavoriteInteractor favoriteInteractor;
    private FragmentView fragmentView;

    public FavoritePresenterImpl(FragmentView fragmentView) {

        favoriteInteractor = new FavoriteInteractorImpl();
        this.fragmentView = fragmentView;
    }

    @Override
    public void loadData(MyFavoriteRecAdapter adapter, String searchRequest, DBMethods dbMethods) {
        favoriteInteractor.loadData(adapter, "", dbMethods);

    }

    @Override
    public void openImage() {

    }

    @Override
    public void deleteFromDb(QueriesData data, DBMethods db) {
        favoriteInteractor.deleteFromDb(data, db, this);
    }

    @Override
    public void onSuccessDeleteFromDB() {

    }
}
