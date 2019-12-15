package com.salamquran.android.salamquran.Lms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salamquran.android.R;

import java.util.List;

public class LmsLevelList_Adaptor extends RecyclerView.Adapter<LmsLevelList_Adaptor.ViewHolder>{

  private List<LmsModel_levelList> mData;
  private LayoutInflater mInflater;
  private Context context;

    // data is passed into the constructor
    public LmsLevelList_Adaptor(Context context, List<LmsModel_levelList> model) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = model;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_lms_level_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
      LmsModel_levelList model = mData.get(position);
      if (model.getFilepic() != null){
        Glide.with(context).load(model.getFilepic()).into(holder.image);
      }
      holder.title.setText(model.getTitle());
      holder.desc.setText(model.getDesc());
      holder.view.setOnClickListener(v -> {
        Intent levelList = new Intent(context, LmsLevel_Activity.class);
        levelList.putExtra("id",model.getId());
        context.startActivity(levelList);
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
          image = itemView.findViewById(R.id.images);
          title = itemView.findViewById(R.id.title);
          desc = itemView.findViewById(R.id.desc);
          view = itemView;

        }
  }

}
