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

import com.ermile.salamquran.Function.Utility.even_odd;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {


    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = this.getContext();

        String text= getArguments().getString("msg");

        final View view = Objects.requireNonNull(inflater).inflate(R.layout.item_quran , container , false);

        final LinearLayout background_slide = view.findViewById(R.id.background_slide);

        TextView TextQuran_textview = new TextView(context);
        TextQuran_textview.setTextColor(Color.parseColor("#000000"));
        TextQuran_textview.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextQuran_textview.setGravity(View.TEXT_ALIGNMENT_CENTER);
        background_slide.addView(TextQuran_textview);

        TextQuran_textview.setTextSize(21f);
        TextQuran_textview.setText(text);

        return view;
    }

    static BlankFragment2 newInstance(String text) {

        BlankFragment2 f = new BlankFragment2();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
