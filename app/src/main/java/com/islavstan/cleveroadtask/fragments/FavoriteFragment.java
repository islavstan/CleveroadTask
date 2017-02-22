package com.islavstan.cleveroadtask.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.islavstan.cleveroadtask.R;
import com.islavstan.cleveroadtask.adapters.MyFavoriteRecAdapter;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.listeners.UserActionsListener;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.presenter.FavoritePresenter;
import com.islavstan.cleveroadtask.presenter.FavoritePresenterImpl;
import com.islavstan.cleveroadtask.presenter.ResultPresenter;
import com.islavstan.cleveroadtask.view.FragmentView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment  extends Fragment implements FragmentView {
    List<QueriesData> queriesDataList = new ArrayList<>();
    RecyclerView recyclerView;
    MyFavoriteRecAdapter adapter;
    FavoritePresenter presenter;
    DBMethods db;
    LinearLayoutManager mLayoutManager;
    private boolean isLoading = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);
        presenter = new FavoritePresenterImpl(this);
        db = new DBMethods(getActivity());
        loadUI(v);
        loadData();
        return v;

    }

    public void update(String textSearch) {
    }


    @Override
    public void loadUI(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        adapter = new MyFavoriteRecAdapter(queriesDataList, listener, this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

      /*  recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = mLayoutManager.getChildCount();//смотрим сколько элементов на экране
                int totalItemCount = mLayoutManager.getItemCount();//сколько всего элементов
                int firstVisibleItems = mLayoutManager.findFirstVisibleItemPosition();//какая позиция первого элемента
                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItems) >= totalItemCount) {
                        isLoading = true;
                        adapter.loadMore();
                    }
                }
            }
        });*/


    }


    UserActionsListener listener = new UserActionsListener() {
        @Override
        public void openImage(QueriesData data) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            PhotoFragment fragment = new PhotoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("srcFromDB", data.getImagePath());
            fragment.setArguments(bundle);
            transaction.replace(R.id.container,fragment,"photo");
            transaction.addToBackStack(null);
            transaction.commit();
        }

        @Override
        public void saveToDB(QueriesData data) {


        }

        @Override
        public void deleteFromDB(QueriesData data) {
            presenter.deleteFromDb(data, db);

        }
    };

    @Override
    public void loadData() {

        presenter.loadData(adapter, "", db);
    }

    @Override
    public void showSuccessSaveToast() {

    }

    @Override
    public void showSuccessDeleteToast() {

    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

}