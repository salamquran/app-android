package com.ermile.salamquran.Actitvty.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ermile.salamquran.Function.Utility.FileManager;
import com.ermile.salamquran.Function.Utility.even_odd;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.tag;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> arrayList = new ArrayList<>();

    public MyAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quran, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bottom_numberPage.setText(position + "");

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout background_slide ;
        ImageView cutPage_R ;
        ImageView cutPage_L ;
        ImageView spacePage_L ;
        ImageView spacePage_R ;
        TextView Top_surah ;
        TextView Top_juz ;
        TextView bottom_numberPage ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            background_slide = itemView.findViewById(R.id.background_slide);

            cutPage_R = itemView.findViewById(R.id.cutPage_R);
            cutPage_L = itemView.findViewById(R.id.cutPage_L);
            spacePage_L = itemView.findViewById(R.id.spacePage_L);
            spacePage_R = itemView.findViewById(R.id.spacePage_R);

            Top_surah = itemView.findViewById(R.id.quranItemViewPager_titleSurah);
            Top_juz = itemView.findViewById(R.id.quranItemViewPager_titleJuz);
            bottom_numberPage = itemView.findViewById(R.id.quranItemViewPager_pageNumber);
        }
    }
}
