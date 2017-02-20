package com.islavstan.cleveroadtask.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.islavstan.cleveroadtask.R;
import com.islavstan.cleveroadtask.listeners.UserActionsListener;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<QueriesData> queriesDataList;
    private UserActionsListener mItemListener;

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
        final QueriesData data = queriesDataList.get(position);
        holder.name.setText(data.getTitle());
        if (data.getPagemap() != null) {
            if (data.getPagemap().getCseThumbnailData() != null) {
                String src = data.getPagemap().getCseThumbnailData().get(0).getSrc();
                Picasso.with(holder.image.getContext()).load(src).placeholder(R.drawable.ic_picture).into(holder.image);
            }
        }


    }

    public void loadData(List<QueriesData> queriesDatas) {
        if (queriesDatas.size() != 0) {
            queriesDataList.clear();
            queriesDataList.addAll(queriesDatas);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return queriesDataList.size();
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
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            mItemListener.openImage();
        }
    }
}