package com.islavstan.cleveroadtask.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kimkevin.cachepot.CachePot;
import com.islavstan.cleveroadtask.R;
import com.islavstan.cleveroadtask.listeners.UserActionsListener;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;



public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<QueriesData> queriesDataList;
    private UserActionsListener mItemListener;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    public MyRecyclerViewAdapter(List<QueriesData> queriesDataList, UserActionsListener mItemListener) {
        this.queriesDataList = queriesDataList;
        this.mItemListener = mItemListener;
    }


    @Override
    public MyRecyclerViewAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyRecyclerViewAdapter.CustomViewHolder(itemView, mItemListener);
    }

    @Override
    public void onBindViewHolder(final MyRecyclerViewAdapter.CustomViewHolder holder, int position) {
        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }


        final QueriesData data = queriesDataList.get(position);
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(data.isSelected());
        holder.name.setText(data.getTitle());
        if (data.getPagemap() != null) {
            if (data.getPagemap().getCseThumbnailData() != null) {
                String src = data.getPagemap().getCseThumbnailData().get(0).getSrc();
                Picasso.with(holder.image.getContext()).load(src).placeholder(R.drawable.ic_picture).into(holder.image);


            }
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.checkBox.isChecked()) {
                    mItemListener.saveToDB(queriesDataList.get(holder.getAdapterPosition()));
                    queriesDataList.get(holder.getAdapterPosition()).setSelected(b);
                } else {
                    queriesDataList.get(holder.getAdapterPosition()).setSelected(b);
                    mItemListener.deleteFromDB(queriesDataList.get(holder.getAdapterPosition()));
                }
            }
        });


    }

    public void loadData(List<QueriesData> queriesDatas) {
        if (queriesDatas.size() != 0) {
            queriesDataList.clear();
            queriesDataList.addAll(queriesDatas);
            notifyDataSetChanged();
            isLoading = false;
        }
    }


    @Override
    public int getItemCount() {
        return queriesDataList.size();
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    public void addMore(List<QueriesData> queriesDatas) {
        queriesDataList.addAll(queriesDatas);
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView image;
        CheckBox checkBox;

        public CustomViewHolder(View itemView, UserActionsListener listener) {
            super(itemView);
            mItemListener = listener;
            name = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.image);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            image.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            mItemListener.openImage(queriesDataList.get(getAdapterPosition()));

        }
    }
}