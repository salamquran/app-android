package com.ermile.salamquran.android.salamquran.Mag;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ermile.salamquran.android.R;
import java.util.ArrayList;


public class MagAdaptor extends RecyclerView.Adapter<MagAdaptor.ViewHolder>{

  private ArrayList<MagModel.list_long> mData;
  private LayoutInflater mInflater;
  private Context context;

  // data is passed into the constructor
  public MagAdaptor(Context context, ArrayList<MagModel.list_long> model) {
    this.context = context;
    this.mInflater = LayoutInflater.from(context);
    this.mData = model;
  }

  // inflates the row layout from xml when needed
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.item_mag_news_list, parent, false);
    return new ViewHolder(view);
  }

  // binds the data to the TextView in each row
  @Override
  public void onBindViewHolder(final ViewHolder holder, final int position) {
    MagModel.list_long model = mData.get(position);
    Glide.with(context).load(model.getImage()).into(holder.image);
    holder.title.setText(model.getTitle());
    holder.desc.setText(model.getExcerpt());
    holder.view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent levelList = new Intent(context, MagActivity.class);
        levelList.putExtra("title",model.getTitle());
        levelList.putExtra("excerpt",model.getExcerpt());
        levelList.putExtra("content",model.getContent());
        levelList.putExtra("image",model.getImage());
        if (model.getGallery() != null){
          levelList.putExtra("gallery",model.getGallery());
        }
        if (model.getSubtitle() != null){
          levelList.putExtra("subtitle",model.getSubtitle());
        }
        context.startActivity(levelList);
      }
    });
  }

  // total number of rows
  @Override
  public int getItemCount() {
    return mData.size();
  }

  // stores and recycles views as they are scrolled off screen
  public class ViewHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView title,desc;
    View view;

    ViewHolder(View itemView) {
      super(itemView);
      image = itemView.findViewById(R.id.image);
      title = itemView.findViewById(R.id.title);
      desc = itemView.findViewById(R.id.desc);
      view = itemView;

    }
  }

}
