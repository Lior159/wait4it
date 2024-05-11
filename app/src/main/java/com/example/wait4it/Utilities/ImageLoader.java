package com.example.wait4it.Utilities;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.wait4it.R;
import com.google.android.material.imageview.ShapeableImageView;

public class ImageLoader {
    private static Context appContext;

    public ImageLoader(Context context) {
        if (context == null)
            throw new IllegalArgumentException("Context cannot be null");
        appContext = context;
    }

    public void load (String link, ShapeableImageView imageView){
        Glide.
                with(appContext)
                .load(link)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    public void load (int drawableId, ShapeableImageView imageView){
        Glide.
                with(appContext)
                .load(drawableId)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
