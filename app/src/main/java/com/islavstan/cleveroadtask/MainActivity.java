package com.islavstan.cleveroadtask;

import android.app.SearchManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.islavstan.cleveroadtask.adapters.MyViewPagerAdapter;
import com.islavstan.cleveroadtask.api.ApiClient;
import com.islavstan.cleveroadtask.model.Queries;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.point.GetDataPoint;
import com.islavstan.cleveroadtask.view.MainView;
import com.lapism.searchview.SearchView;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView {

    MyViewPagerAdapter adapter;
    ViewPager viewPager;
    SearchView searchView;
    int tabPositionSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUI();
        loadSearchView();


    }

    @Override
    public void loadUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.main)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.favorite)));
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(2);
        adapter = new MyViewPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabPositionSelected = position;
                refreshTab(tabPositionSelected, "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

    @Override
    public void loadSearchView() {
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.RefreshTab(tabPositionSelected, newText);


                return false;
            }
        });
    }

    private void refreshTab(int position, String textSearch) {
        adapter.refreshAdapter(position, textSearch);
    }
}
