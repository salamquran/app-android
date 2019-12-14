package com.salamquran.android.salamquran.Lms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salamquran.android.R;

import java.util.List;

public class LmsAdaptor extends RecyclerView.Adapter<LmsAdaptor.ViewHolder> {

    private List<LmsModel> mData;
    private LayoutInflater mInflater;
    private Context context;
    private boolean duble = false;
    ImageView imageView;

    // data is passed into the constructor
    public LmsAdaptor(Context context, List<LmsModel> model) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = model;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_lms_group, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
      LmsModel model = mData.get(position);
      Glide.with(context).load(model.getImage()).into(holder.image);
      holder.title.setText(model.getTitle());
      holder.techerName.setText(model.getTecherName());
      holder.countLevel.setText(model.getType_title());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
      ImageView image;
      TextView title,techerName,countLevel;

        ViewHolder(View itemView) {
          super(itemView);
          image = itemView.findViewById(R.id.image);
          title = itemView.findViewById(R.id.title);
          techerName = itemView.findViewById(R.id.techer_name);
          countLevel = itemView.findViewById(R.id.count_level);

        }

    }


}
