package com.ermile.salamquran.android.salamquran.Learn;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ermile.salamquran.android.R;
import java.util.ArrayList;

public class LearnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context mContext;
  private ArrayList<LearnModel> itemMains;
  private ArrayList<LearnModel.group> itemGroup;
  private ArrayList<LearnModel.level_list> itemLevel_List;
  private int total_types;


  public static class holder_group extends RecyclerView.ViewHolder {

    View view;
    ImageView image;
    TextView title,desc,type;

    holder_group(View itemView) {
      super(itemView);
      this.view = itemView;
      this.image = itemView.findViewById(R.id.image);
      this.title = itemView.findViewById(R.id.title);
      this.desc = itemView.findViewById(R.id.desc);
      this.type = itemView.findViewById(R.id.type_title);
    }
  }

  public static class holder_level_list extends RecyclerView.ViewHolder {

    View view;
    ImageView image;
    TextView title,desc;

    holder_level_list(View itemView) {
      super(itemView);
      this.view = itemView;
      this.image = itemView.findViewById(R.id.image);
      this.title = itemView.findViewById(R.id.title);
      this.desc = itemView.findViewById(R.id.desc);
    }
  }

  public LearnAdapter(Context context,
                      ArrayList<LearnModel> model_Main,
                      ArrayList<LearnModel.group> model_Group,
                      ArrayList<LearnModel.level_list> model_Level_list) {
    this.mContext = context;
    this.itemMains = model_Main;
    if (model_Group!=null){
      this.itemGroup = model_Group;
    }
    if (model_Level_list!=null){
      this.itemLevel_List = model_Level_list;
    }
    total_types = itemMains.size();

  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view;
    switch (viewType) {
      case LearnModel.GROUP :
        view = LayoutInflater
            .from(parent.getContext())
            .inflate(
                R.layout.item_learn_group,
                parent,
                false);
        return new holder_group(view);

      case LearnModel.LEVEL_LIST  :
        view = LayoutInflater
            .from(parent.getContext())
            .inflate(
                R.layout.item_learn_level_list,
                parent,
                false);
        return new holder_level_list(view);
    }
    return null;
  }


  @Override
  public int getItemViewType(int position) {

    switch (itemMains.get(position).type) {
      case 100:
        return LearnModel.GROUP;
      case 200:
        return LearnModel.LEVEL_LIST;
      default:
        return -1;
    }


  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

    final LearnModel item_main = itemMains.get(listPosition);
    if (item_main != null) {
      switch (item_main.type) {
        case LearnModel.GROUP:
          final LearnModel.group item_group = itemGroup.get(listPosition);
          holder_group hGroup = ((holder_group) holder);
          if (item_group.getImage()!=null){
            Glide.with(mContext)
                .load(item_group.getImage())
                .into( hGroup.image );
          }
          hGroup.title.setText(item_group.getTitle());
          hGroup.desc.setText(item_group.getDesc());
          hGroup.type.setText(item_group.getType_title());
          hGroup.view.setOnClickListener(v -> {
            if (item_group.getId() != null){
              Intent intent = new Intent(mContext, LearnActivity_level_list.class);
              intent.putExtra("id",item_group.getId());
              mContext.startActivity(intent);
            }

          });

          break;

        case LearnModel.LEVEL_LIST:
          final LearnModel.level_list item_level_list = itemLevel_List.get(listPosition);
          holder_level_list hLeve_list = ((holder_level_list) holder);
          /*if (item_level_list.getFilepic()!=null){
            Glide.with(mContext)
                .load(item_level_list.getFilepic())
                .into(hLeve_list.image );
          }*/
          hLeve_list.title.setText(item_level_list.getTitle());
          hLeve_list.desc.setText(item_level_list.getType_title());

          hLeve_list.view.setOnClickListener(v -> {
            if (item_level_list.getId() != null){
              Intent intents = new Intent(mContext, LearnActivity_level_info.class);
              intents.putExtra("id",item_level_list.getId());
              intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              mContext.startActivity(intents);
            }
          });
          break;
      }
    }
  }

  @Override
  public int getItemCount() {
    return itemMains.size();
  }

}
