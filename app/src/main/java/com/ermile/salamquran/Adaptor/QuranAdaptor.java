package com.ermile.salamquran.Adaptor;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ermile.salamquran.Function.Utility.even_odd;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;

import java.util.Objects;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class QuranAdaptor extends androidx.viewpager.widget.PagerAdapter {

    @Override
    public int getCount() {
        return 605;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        // Static Methods
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View view = Objects.requireNonNull(inflater).inflate(R.layout.item_quran , container , false);

        final LinearLayout background_slide = view.findViewById(R.id.background_slide);

        final ImageView cutPage_R = view.findViewById(R.id.cutPage_R);
        final ImageView cutPage_L = view.findViewById(R.id.cutPage_L);
        final ImageView spacePage_L = view.findViewById(R.id.spacePage_L);
        final ImageView spacePage_R = view.findViewById(R.id.spacePage_R);


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

        int testLine =0;

        if (position > 0){

            Typeface font = Typeface.createFromAsset(container.getContext().getAssets(), "font/"+"p"+position+".ttf");
            Typeface font_besmellah = Typeface.createFromAsset(container.getContext().getAssets(), "font/bismillah.ttf");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            SQLiteDatabase mydb = new MyDatabase(container.getContext()).getReadableDatabase();
            Cursor pageData = mydb.rawQuery("SELECT * FROM quran_word WHERE page="+position, null);
            while (pageData.moveToNext()) {
            String text = pageData.getString(pageData.getColumnIndex("text"));
            String code = pageData.getString(pageData.getColumnIndex("code"));
            final int aya = pageData.getInt(pageData.getColumnIndex("aya"));
            final int sura = pageData.getInt(pageData.getColumnIndex("sura"));
            String charType = pageData.getString(pageData.getColumnIndex("char_type"));
            String class_name = pageData.getString(pageData.getColumnIndex("class_name"));
            int line = pageData.getInt(pageData.getColumnIndex("line"));
            int page = pageData.getInt(pageData.getColumnIndex("page"));
            int positions = pageData.getInt(pageData.getColumnIndex("position"));
            String audio = pageData.getString(pageData.getColumnIndex("audio"));
            int index = pageData.getInt(pageData.getColumnIndex("index"));


                if (
                        aya == 1
                        && positions == 1
                        && page > 1
                        && sura != 9
                )
                {
                    LinearLayout linearLayout_Lines = new LinearLayout(container.getContext());
                    linearLayout_Lines.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout_Lines.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayout_Lines.setLayoutParams(layoutParams);
                    background_slide.addView(linearLayout_Lines);
                    TextView TextQuran_textviews = new TextView(container.getContext());
                    TextQuran_textviews.setTextColor(Color.parseColor("#000000"));
                    TextQuran_textviews.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    TextQuran_textviews.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    linearLayout_Lines.addView(TextQuran_textviews);
                    TextQuran_textviews.setTag(index);

                    TextQuran_textviews.setTextSize(25f);
                    TextQuran_textviews.setTypeface(font_besmellah);
                    TextQuran_textviews.setText("﷽");

                }

                if(testLine < line){
                    linearLayout_Line = new LinearLayout(container.getContext());
                    linearLayout_Line.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout_Line.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayout_Line.setLayoutParams(layoutParams);
                    background_slide.addView(linearLayout_Line);
                    testLine =line;
                }

                if (linearLayout_Line != null){

                    TextQuran_textview = new TextView(container.getContext());
                    TextQuran_textview.setTextColor(Color.parseColor("#000000"));
                    TextQuran_textview.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    TextQuran_textview.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    linearLayout_Line.addView(TextQuran_textview);
                    TextQuran_textview.setTag(index);

                    TextQuran_textview.setTextSize(21f);
                    TextQuran_textview.setTypeface(font);
                    TextQuran_textview.setText(Html.fromHtml(code).toString());

                    final String getTag_textQuran = TextQuran_textview.getTag().toString();
                    TextQuran_textview.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            for (int rq=0; rq<= background_slide.getChildCount();rq++){
                                LinearLayout rowQuran = (LinearLayout) background_slide.getChildAt(rq);
                                if (rowQuran != null){
                                    for (int wq=0; wq <= rowQuran.getChildCount(); wq++){
                                        TextView wordQuran = (TextView) rowQuran.getChildAt(wq);
                                        if (wordQuran != null){
                                            if (wordQuran.getTag().toString().equals(getTag_textQuran)){
                                                wordQuran.setBackgroundColor(Color.parseColor("#60B3B0B0"));
                                            }
                                        }
                                    }
                                }
                            }
                            return true;
                        }
                    });
                }
            }
            pageData.close();
            mydb.close();
        }
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView( (RelativeLayout) object);
    }


}