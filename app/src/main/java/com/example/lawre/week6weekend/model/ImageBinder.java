package com.example.lawre.week6weekend.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageBinder
{
    @BindingAdapter({"bind:image_url"})
    public static void loadImage(ImageView imageView, String url)
    {
        Glide.with(imageView).load(url).into(imageView);
    }
}
