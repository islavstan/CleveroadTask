package com.islavstan.cleveroadtask.presenter;

import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.fragments.ResultFragment;
import com.islavstan.cleveroadtask.interactor.ResultInteractor;
import com.islavstan.cleveroadtask.interactor.ResultInteractorImpl;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.view.FragmentView;
import com.squareup.picasso.Picasso;


public class ResultPresenterImpl implements ResultPresenter, ResultInteractor.ResultFinishedListener {


    private ResultInteractor resultInteractor;
    private FragmentView fragmentView;

    public ResultPresenterImpl(FragmentView fragmentView) {

        resultInteractor = new ResultInteractorImpl();
        this.fragmentView = fragmentView;
    }


    @Override
    public void loadData(MyRecyclerViewAdapter adapter, String searchRequest) {
        resultInteractor.loadData(adapter, searchRequest);
    }

    @Override
    public void openImage() {

    }

    @Override
    public void saveToDB(QueriesData data, DBMethods db, Picasso picasso) {
        resultInteractor.saveToDB(data, db, picasso, this);
    }

    @Override
    public void deleteFromDb(QueriesData data, DBMethods db) {

    }

    @Override
    public void onSuccessSaveToDB() {
        fragmentView.showSuccessSaveToast();
    }

    @Override
    public void onSuccessDeleteFromDB() {
        fragmentView.showSuccessDeleteToast();
    }
}
