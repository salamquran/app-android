package com.ermile.salamquran.android.salamquran;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Utility.ImageViewerActivity;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class Adapter {

  public static class slider extends RecyclerView.Adapter<slider.ViewHolder>{

    private ArrayList<String> itemSliderList;
    private LayoutInflater mInflater;
    Context context;
    boolean canZoomOnImage = false;

    // data is passed into the constructor
    public slider(Context context, ArrayList<String> itemSliderList , boolean canZoomOnImage) {
      this.context = context;
      this.mInflater = LayoutInflater.from(context);
      this.itemSliderList = itemSliderList;
      this.canZoomOnImage = canZoomOnImage;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view;
      if (canZoomOnImage){
        view = mInflater.inflate(R.layout.item_image_viewer, parent, false);
      }
      else {
        view = mInflater.inflate(R.layout.item_slider, parent, false);
      }

      return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
      String item = itemSliderList.get(position);
      if (item != null){
        if (canZoomOnImage){
          Glide.with(context).load(item).into(holder.photoView);
        }else {
          Glide.with(context).load(item).into(holder.imageViews);
          holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent levelList = new Intent(context, ImageViewerActivity.class);
              levelList.putExtra("image",item);
              levelList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              context.startActivity(levelList);
            }
          });
        }
      }

    }

    // total number of rows
    @Override
    public int getItemCount() {
      return itemSliderList.size();
    }


    // stores and recycles views as they are scrolled off screen
    private class ViewHolder extends RecyclerView.ViewHolder{
      View view;
      ImageView imageViews;
      PhotoView photoView;

      ViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        if (canZoomOnImage){
          photoView = itemView.findViewById(R.id.slider_image);
        }else {
          imageViews = itemView.findViewById(R.id.slider_image);
        }
      }
    }


  }
}
