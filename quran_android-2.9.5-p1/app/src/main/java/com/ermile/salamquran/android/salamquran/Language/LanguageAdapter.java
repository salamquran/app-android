package com.ermile.salamquran.android.salamquran.Language;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Utility.SaveManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {

  private List<LanguageModel> itemList;
  private Context mContext;

  public LanguageAdapter(List<LanguageModel> itemList, Context mContext) {
    this.itemList = itemList;
    this.mContext = mContext;
  }

  @NotNull
  @Override
  public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

    View aView = LayoutInflater.from(mContext).inflate(R.layout.item_language, parent, false);
    return new MyViewHolder(aView);

  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, final int position) {

    final LanguageModel aItem = itemList.get(position);

    holder.icChoosed.setVisibility(View.GONE);
    holder.titleCountry.setText(aItem.getTitle());
    holder.titleCountry.setTag(aItem.getTag());

    if (aItem.isChBoxVisibel()){
      holder.icChoosed.setVisibility(View.VISIBLE);
    }

    switch (aItem.getTag()){
      case "en":
        holder.imgCountry.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_en));
        break;
      case "fa":
        holder.imgCountry.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_fa));
        break;
      case "ar":
        holder.imgCountry.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_ar));
        break;

    }
/*
        holder.checkLanguage.setVisibility(aItem.getChBoxVisibel());
*/

    holder.view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String choseLanguage = holder.titleCountry.getTag().toString();
        SaveManager.get(mContext).save_app_language(choseLanguage);

      }
    });

  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    View view;
    ImageView imgCountry, icChoosed;
    TextView titleCountry;


    public MyViewHolder(View itemView) {
      super(itemView);
      view = itemView;
      imgCountry = itemView.findViewById(R.id.imo_country);
      icChoosed = itemView.findViewById(R.id.icChoosed);
      titleCountry = itemView.findViewById(R.id.title_country);
/*
            checkLanguage = itemView.findViewById(R.id.checkLanguage);
*/
    }
  }

}
