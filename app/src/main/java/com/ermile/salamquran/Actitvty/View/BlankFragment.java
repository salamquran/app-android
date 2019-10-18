package com.ermile.salamquran.Actitvty.View;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ermile.salamquran.Function.Utility.FileManager;
import com.ermile.salamquran.Function.Utility.even_odd;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;

import java.util.Objects;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        Context context = container.getContext();

        int position = Integer.valueOf(Objects.requireNonNull(getArguments().getString("msg")));

        final View view = Objects.requireNonNull(inflater).inflate(R.layout.item_quran , container , false);

        final LinearLayout background_slide = view.findViewById(R.id.background_slide);

        final ImageView cutPage_R = view.findViewById(R.id.cutPage_R);
        final ImageView cutPage_L = view.findViewById(R.id.cutPage_L);
        final ImageView spacePage_L = view.findViewById(R.id.spacePage_L);
        final ImageView spacePage_R = view.findViewById(R.id.spacePage_R);

        boolean pageInfoIsSet = false;
        final TextView Top_surah = view.findViewById(R.id.quranItemViewPager_titleSurah);
        final TextView Top_juz = view.findViewById(R.id.quranItemViewPager_titleJuz);
        final TextView bottom_numberPage = view.findViewById(R.id.quranItemViewPager_pageNumber);


        if (even_odd.isOdd(position)){
            cutPage_R.setVisibility(View.VISIBLE);
            spacePage_L.setVisibility(View.VISIBLE);
        }
        else {
            cutPage_L.setVisibility(View.VISIBLE);
            spacePage_R.setVisibility(View.VISIBLE);
        }
        LinearLayout linearLayout_Line = null;
        TextView TextQuran_textview  ;


        for (int i = 0; i < 200; i++) {
            TextQuran_textview = new TextView(context);
            TextQuran_textview.setTextColor(Color.parseColor("#000000"));
            TextQuran_textview.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextQuran_textview.setGravity(View.TEXT_ALIGNMENT_CENTER);
            background_slide.addView(TextQuran_textview);
            TextQuran_textview.setTextSize(21f);
            TextQuran_textview.setText(i+"");
        }

        int testLine =0;
        Typeface font;
        if (position > 0){

            Typeface font_besmellah = Typeface.createFromAsset(context.getAssets(), "font/bismillah.ttf");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    70);

            SQLiteDatabase mydb = new MyDatabase(context).getReadableDatabase();
            Cursor pageData = mydb.rawQuery("SELECT text,code,aya,juz,sura,char_type,class_name,line,page,position,audio,\"index\" FROM quran_word WHERE page="+position, null);
            while (pageData.moveToNext()) {

            }
            pageData.close();
            mydb.close();
        }










        return view;
    }

    static BlankFragment newInstance(String text) {

        BlankFragment f = new BlankFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
