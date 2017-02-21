package com.islavstan.cleveroadtask.adapters;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.islavstan.cleveroadtask.R;
import com.islavstan.cleveroadtask.listeners.UserActionsListener;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;


public class MyFavoriteRecAdapter extends RecyclerView.Adapter<MyFavoriteRecAdapter.CustomViewHolder> {
    private List<QueriesData> queriesDataList;
    private UserActionsListener mItemListener;
    String root = Environment.getExternalStorageDirectory().toString();

    public MyFavoriteRecAdapter(List<QueriesData> queriesDataList, UserActionsListener mItemListener) {
        this.queriesDataList = queriesDataList;
        this.mItemListener = mItemListener;
    }


    @Override
    public MyFavoriteRecAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyFavoriteRecAdapter.CustomViewHolder(itemView, mItemListener);
    }

    @Override
    public void onBindViewHolder(final MyFavoriteRecAdapter.CustomViewHolder holder, int position) {
        final QueriesData data = queriesDataList.get(position);
        holder.name.setText(data.getTitle());
        Uri uri = Uri.fromFile(new File(root + "/CleveroadTask/images/" + data.getImagePath()));
        Picasso.with(holder.image.getContext()).load(uri).placeholder(R.drawable.ic_picture).into(holder.image);

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
            checkBox.setChecked(true);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    mItemListener.deleteFromDB(queriesDataList.get(getAdapterPosition()));
                    queriesDataList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                }

            });


        }

        @Override
        public void onClick(View view) {
            mItemListener.openImage(queriesDataList.get(getAdapterPosition()));
        }
    }
}