package com.ermile.salamquran.android.salamquran.Intro;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Utility.ColorUtil;

import java.util.List;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.ViewHolder>{

    private List<IntroModel> itemIntroList;
    private LayoutInflater mInflater;
    Context context;

    // data is passed into the constructor
    public IntroAdapter(Context context, List<IntroModel> itemIntroList) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.itemIntroList = itemIntroList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_intro, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String image = itemIntroList.get(position).getImage();
        String title = itemIntroList.get(position).getTitle();
        final String content = itemIntroList.get(position).getDesc();

        String bg_color_start = itemIntroList.get(position).getBg_color_start();
        String bg_color_end = itemIntroList.get(position).getBg_color_end();
        String title_color = itemIntroList.get(position).getColot_title();
        String desc_color = itemIntroList.get(position).getColot_desc();

        if (image != null){
            Glide.with(context).load(image).into(holder.imageViews);
        }
        holder.title.setText(title);
        holder.content.setText(content);

        if (bg_color_start != null && bg_color_end !=null){
          ColorUtil.setGradient(holder.linearLayout,bg_color_start,bg_color_end);
        }
        if (title_color != null){
            holder.title.setTextColor(Color.parseColor(title_color));
        }
        if (desc_color != null){
            holder.title.setTextColor(Color.parseColor(desc_color));
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return itemIntroList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        ImageView imageViews;
        TextView title,content;

        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_inro_item);
            imageViews = itemView.findViewById(R.id.intro_img);
            title = itemView.findViewById(R.id.intro_title);
            content = itemView.findViewById(R.id.intro_desc);
        }

    }

}
