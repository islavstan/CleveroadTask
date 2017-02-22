package com.islavstan.cleveroadtask.adapters;

import android.content.Context;
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
import android.widget.Toast;

import com.islavstan.cleveroadtask.R;
import com.islavstan.cleveroadtask.fragments.FavoriteFragment;
import com.islavstan.cleveroadtask.listeners.UserActionsListener;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.view.FragmentView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MyFavoriteRecAdapter extends RecyclerView.Adapter<MyFavoriteRecAdapter.CustomViewHolder> {
    private List<QueriesData> queriesDataList;
    private UserActionsListener mItemListener;
    String root = Environment.getExternalStorageDirectory().toString();
    int next = 10;
   FavoriteFragment context;

    List<QueriesData> allItemsList = new ArrayList<>();
    public MyFavoriteRecAdapter(List<QueriesData> queriesDataList, UserActionsListener mItemListener, FavoriteFragment context) {
        this.queriesDataList = queriesDataList;
        this.mItemListener = mItemListener;
        this.context = context;

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
      /*  if (queriesDatas.size() != 0) {
            queriesDataList.clear();
            queriesDataList.addAll(queriesDatas);
            notifyDataSetChanged();
        }*/

        allItemsList = queriesDatas;
        for (int i = 0; i < next; i++) {
            queriesDataList.add(allItemsList.get(i));
        }
        notifyDataSetChanged();

    }
 /*   public void loadMore() {
       *//* if(queriesDataList.size()!=allItemsList.size()) {
            for (int i = next; i < next + 10; i++) {
                queriesDataList.add(allItemsList.get(i));
                notifyItemInserted(i);
                next++;
            }


        }*//*


        context.setLoading(false);
    }
*/


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