package com.ermile.salamquran.Adaptor;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class QuranAdaptor extends androidx.viewpager.widget.PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    public int count = 605; // Slide number

    public QuranAdaptor(Context context) {
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return count;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        // Static Methods
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.item_quran , container , false);
        final LinearLayout background_slide = view.findViewById(R.id.background_slide);

        LinearLayout linearLayout_Line = null;
        TextView TextQuran_textview = null ;

        int testLine =0;

        Typeface font_nabi= ResourcesCompat.getFont(context, R.font.font_nabi);
        Typeface font_p1 = ResourcesCompat.getFont(context, R.font.p1);
        Typeface font_p5 = ResourcesCompat.getFont(context, R.font.p5);
        final Typeface font_bismellah =ResourcesCompat.getFont(context, R.font.bismillah);



        SQLiteDatabase mydb = new MyDatabase(context).getWritableDatabase();
        Cursor pageData = mydb.rawQuery("SELECT * FROM quran_word WHERE page="+position, null);
        while (pageData.moveToNext()) {
            String text = pageData.getString(pageData.getColumnIndex("text"));
            String code = pageData.getString(pageData.getColumnIndex("code"));
            final int aya = pageData.getInt(pageData.getColumnIndex("aya"));
            final int sura = pageData.getInt(pageData.getColumnIndex("sura"));
            String charType = pageData.getString(pageData.getColumnIndex("char_type"));
            int line = pageData.getInt(pageData.getColumnIndex("line"));
            int page = pageData.getInt(pageData.getColumnIndex("page"));
            int positions = pageData.getInt(pageData.getColumnIndex("position"));
            String audio = pageData.getString(pageData.getColumnIndex("audio"));



            if (charType.equals("end")){
                Log.d("", ""+aya+"\n"+page);
            }


            if(testLine < line){
                linearLayout_Line = new LinearLayout(view.getContext());
                linearLayout_Line.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout_Line.setGravity(Gravity.CENTER_HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayout_Line.setLayoutParams(layoutParams);
                background_slide.addView(linearLayout_Line);
                testLine =line;

            }




            if (linearLayout_Line != null){
                TextQuran_textview = new TextView(view.getContext());
                TextQuran_textview.setTextColor(Color.parseColor("#000000"));
                TextQuran_textview.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                TextQuran_textview.setGravity(View.TEXT_ALIGNMENT_CENTER);
                linearLayout_Line.addView(TextQuran_textview);
//                TextQuran_textview.setTag(urlAudio.UrlAudio(aya,sura));

            }

//            final String getTag_textQuran = TextQuran_textview.getTag().toString();
            /*On Long Click Listener
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
            });*/



            switch (page){
                case 1:
                    TextQuran_textview.setTextSize(35f);
                    TextQuran_textview.setText(Html.fromHtml(code).toString());
                    TextQuran_textview.setTypeface(font_p1);
                    break;
                case 5:
                    TextQuran_textview.setTextSize(21f);
                    TextQuran_textview.setText(Html.fromHtml(code).toString());
                    TextQuran_textview.setTypeface(font_p5);
                    break;
                default:
                    if (text != null){
                        TextQuran_textview.setText(" "+text+" ");
                    }else {
                        TextQuran_textview.setText(" ( "+aya+" ) ");
                    }
                    TextQuran_textview.setTextSize(15f);
                    TextQuran_textview.setTypeface(font_nabi);
                    break;
            }


        }
        pageData.close();
        mydb.close();

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView( (LinearLayout) object);
    }
}