package com.example.wait4it.Utilities;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class ImageLoader {
    private static Context appContext;

    public ImageLoader(Context context) {
        if (context == null)
            throw new IllegalArgumentException("Context cannot be null");
        appContext = context;
    }

    public void load (String link,int secondaryDrawableId, ShapeableImageView imageView){
        Glide.
                with(appContext)
                .load(link)
                .placeholder(secondaryDrawableId)
                .into(imageView);
    }

    public void load (int primaryDrawableId, int secondaryDrawableId, ShapeableImageView imageView){
        Glide.
                with(appContext)
                .load(primaryDrawableId)
                .placeholder(secondaryDrawableId)
                .into(imageView);
    }
}
