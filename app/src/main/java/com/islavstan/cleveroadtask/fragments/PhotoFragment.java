package com.islavstan.cleveroadtask.fragments;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.github.kimkevin.cachepot.CachePot;
import com.islavstan.cleveroadtask.R;
import com.squareup.picasso.Picasso;

import java.io.File;


public class PhotoFragment extends Fragment {
    ImageButton backButton;
    ImageView image;
    String root = Environment.getExternalStorageDirectory().toString();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_browse, container, false);
        Bundle bundle = this.getArguments();

        backButton = (ImageButton) v.findViewById(R.id.btn_close);
        image = (ImageView) v.findViewById(R.id.image);

        loadImage(bundle);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFragment();
            }
        });
        return v;
    }

    public void closeFragment() {
        getActivity().onBackPressed();
    }

    public void loadImage(Bundle bundle) {
        if (bundle != null && bundle.getString("src") != null) {
            String src = bundle.getString("src");
            Picasso.with(getActivity()).load(src).into(image);
        } else if (bundle != null && bundle.getString("srcFromDB") != null) {

            Uri uri = Uri.fromFile(new File(root + "/CleveroadTask/images/" + bundle.getString("srcFromDB")));

            Picasso.with(getActivity()).load(uri).into(image);
        }

    }
}
