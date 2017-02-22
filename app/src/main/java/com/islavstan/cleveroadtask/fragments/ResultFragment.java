package com.islavstan.cleveroadtask.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.islavstan.cleveroadtask.R;
import com.islavstan.cleveroadtask.api.ApiClient;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.listeners.UserActionsListener;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.loader.Callback;
import com.islavstan.cleveroadtask.loader.QueriesLoader;
import com.islavstan.cleveroadtask.loader.RetrofitLoaderManager;
import com.islavstan.cleveroadtask.model.Queries;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.point.GetDataPoint;
import com.islavstan.cleveroadtask.presenter.ResultPresenter;
import com.islavstan.cleveroadtask.presenter.ResultPresenterImpl;
import com.islavstan.cleveroadtask.view.FragmentView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class ResultFragment extends Fragment implements Callback<Queries>, FragmentView {
    List<QueriesData> queriesDataList = new ArrayList<>();
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    ResultPresenter presenter;
    DBMethods db;
    Picasso picasso;
    String search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        presenter = new ResultPresenterImpl(this);
        db = new DBMethods(getActivity());
        picasso = Picasso.with(getActivity());
        loadUI(v);
        loadData();

        return v;

    }


    private void initLoader(String search, int index) {
        final GetDataPoint point = ApiClient.getRetrofit().create(GetDataPoint.class);
        QueriesLoader loader = new QueriesLoader(getActivity(), point, search, index);
        RetrofitLoaderManager.init(getActivity().getLoaderManager(), 0, loader, this);

    }


    @Override
    public void loadUI(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        adapter = new MyRecyclerViewAdapter(queriesDataList, listener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setLoadMoreListener(new MyRecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        int index = queriesDataList.size() - 1;
                        Log.d("stas", "loadMore(index)");
                        loadMore(index);
                    }
                });
            }
        });

    }

    private void loadMore(int index) {
        initLoader(search, index);

    }

    UserActionsListener listener = new UserActionsListener() {
        @Override
        public void openImage(QueriesData data) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            PhotoFragment fragment = new PhotoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("src", data.getPagemap().getCseThumbnailData().get(0).getSrc());
            fragment.setArguments(bundle);
            transaction.replace(R.id.container, fragment, "photo");
            transaction.addToBackStack(null);
            transaction.commit();
        }

        @Override
        public void saveToDB(QueriesData data) {
            presenter.saveToDB(data, db, picasso);
        }

        @Override
        public void deleteFromDB(QueriesData data) {
            presenter.deleteFromDb(data, db);
        }
    };


    @Override
    public void showSuccessSaveToast() {
        Toast.makeText(getActivity(), getResources().getString(R.string.add_to_favorine), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessDeleteToast() {
        Toast.makeText(getActivity(), getResources().getString(R.string.delete_from_favorine), Toast.LENGTH_SHORT).show();
    }

    public void update(String textSearch) {
        search = textSearch;
        initLoader(textSearch, 1);

    }


    @Override
    public void onFailure(Exception ex) {
        Log.d("stas", ex.getMessage());

    }

    @Override
    public void onSuccess(Queries result) {
        getActivity().getLoaderManager().destroyLoader(0);
        presenter.loadData(adapter, result);
    }

    @Override
    public void loadData() {

    }

}
