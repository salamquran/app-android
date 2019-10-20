package com.ermile.salamquran.Adaptor;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.ermile.salamquran.Function.Utility.FileManager;
import com.ermile.salamquran.Function.Utility.even_odd;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.QuranValue;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.value;

import java.util.Objects;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class QuranAdaptor extends androidx.viewpager.widget.PagerAdapter {


    private int lineRender = 1;

    private onTochListener tochListener;

    public QuranAdaptor(onTochListener tochListener) {
        this.tochListener = tochListener;
    }

    @Override
    public int getCount() {
        return 605;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint({"ResourceType", "SetTextI18n", "ClickableViewAccessibility"})
    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        Context context = container.getContext();

        // Static Methods
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
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
        TextView TextQuran_textview = null;

        int testLine =0;
        lineRender = 1;
        Typeface font;
        if (position > 0){

            if (position <= 10){
                font = Typeface.createFromFile(FileManager.getFile_storage("font/Quran_v1","p"+position+".ttf").getPath());
            }else {
                font = Typeface.createFromAsset(context.getAssets(), "font/"+"p"+position+".ttf");
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    70);

            SQLiteDatabase mydb = new MyDatabase(context).getReadableDatabase();
            Cursor pageData = mydb.rawQuery("SELECT * FROM quran_word WHERE page="+position, null);
            int dbRenderCunt = 0;
            while (pageData.moveToNext()) {
                dbRenderCunt++;

                String code = pageData.getString(pageData.getColumnIndex("code"));
                final int aya = pageData.getInt(pageData.getColumnIndex("aya"));
                final int juz = pageData.getInt(pageData.getColumnIndex("juz"));
                final int sura = pageData.getInt(pageData.getColumnIndex("sura"));
                int line = pageData.getInt(pageData.getColumnIndex("line"));
                int page = pageData.getInt(pageData.getColumnIndex("page"));
                int positions = pageData.getInt(pageData.getColumnIndex("position"));
                int index = pageData.getInt(pageData.getColumnIndex("index"));

                if (!pageInfoIsSet){
                    pageInfoIsSet = true;
                    Top_surah.setText(context.getString(R.string.surah)+sura);
                    Top_juz.setText(context.getString(R.string.juz)+juz);
                    bottom_numberPage.setText(String.valueOf(page));
                }


                if (line == 14 && dbRenderCunt == pageData.getCount()){
                    Log.e(tag.important, "Page: "+page +" ONE TitleVars" );
                    crateTitelVers(context,background_slide,sura+1);
                    lineRender++;
                    lineRender++;
                }

                if(testLine < line){
                    Log.d(tag.important, "line: "+line+" - line Render: "+lineRender);
                    int t = line - lineRender;
                    Log.d(tag.important, "----------------------------------------->line: "+line+" - line Render: "+lineRender+" - t: "+t);
                    switch (t){
                        case 2:
                            Log.e(tag.important, "Page: "+page +" TWO" );
                            lineRender = line+1;
                            crateTitelVers(context,background_slide,sura);
                            crateBesmellah(context,background_slide,index);

                            break;
                        case 1:
                            if (line == 2){
                                Log.e(tag.important, "Page: "+page +" ONE > Besmellah" );
                                crateBesmellah(context,background_slide,index);
                                lineRender++;
                                lineRender++;
                            }
                            break;

                            default:
                                lineRender++;
                                break;
                    }


                    linearLayout_Line = new LinearLayout(context);
                    linearLayout_Line.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout_Line.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayout_Line.setLayoutParams(layoutParams);
                    background_slide.addView(linearLayout_Line);
                    testLine =line;
                }

                if (linearLayout_Line != null){
                    crateWordQuran(context,linearLayout_Line,TextQuran_textview,font,null,code,index,page);
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

    public interface onTochListener{
        void wordOnclickListener();
        void wordOnLongclickListener();
    }


    private void crateWordQuran(Context context,LinearLayout linearLayout_Line ,TextView wordQuran,Typeface font,String text , String code, int index , int page ){
        wordQuran = new TextView(context);
        wordQuran.setTextColor(Color.parseColor("#000000"));
        wordQuran.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        wordQuran.setGravity(View.TEXT_ALIGNMENT_CENTER);
        linearLayout_Line.addView(wordQuran);
        wordQuran.setTag(index);

        wordQuran.setTextSize(21f);
        wordQuran.setTypeface(font);
        wordQuran.setText(Html.fromHtml(code).toString());

        wordQuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tochListener.wordOnclickListener();
            }
        });
        wordQuran.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                tochListener.wordOnLongclickListener();
                return true;
            }
        });

    }

    private void crateTitelVers(Context context , LinearLayout background_slide,int vars ){
        Typeface fontNabi = ResourcesCompat.getFont(context,R.font.font_nabi);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 10;
        layoutParams.bottomMargin = 10;
        layoutParams.leftMargin = 30;
        layoutParams.rightMargin = 30;

        LinearLayout linearLayout_Lines = new LinearLayout(context);
        linearLayout_Lines.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_Lines.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout_Lines.setLayoutParams(layoutParams);
        background_slide.addView(linearLayout_Lines);
        TextView TextQuran_textviews = new TextView(context);
        TextQuran_textviews.setTextColor(Color.parseColor("#000000"));
        TextQuran_textviews.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextQuran_textviews.setGravity(View.TEXT_ALIGNMENT_CENTER);
        linearLayout_Lines.addView(TextQuran_textviews);

        TextQuran_textviews.setTextSize(20f);
        TextQuran_textviews.setBackgroundResource(R.drawable.surh_header);
        TextQuran_textviews.setTypeface(fontNabi);
        TextQuran_textviews.setText(QuranValue.listSura[vars]);

    }
    private void crateBesmellah(Context context , LinearLayout background_slide,int index ){
        Typeface font_besmellah = Typeface.createFromAsset(context.getAssets(), "font/bismillah.ttf");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout_Lines = new LinearLayout(context);
        linearLayout_Lines.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_Lines.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout_Lines.setLayoutParams(layoutParams);
        background_slide.addView(linearLayout_Lines);
        TextView TextQuran_textviews = new TextView(context);
        TextQuran_textviews.setTextColor(Color.parseColor("#000000"));
        TextQuran_textviews.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextQuran_textviews.setGravity(View.TEXT_ALIGNMENT_CENTER);
        linearLayout_Lines.addView(TextQuran_textviews);
        TextQuran_textviews.setTag(index);

        TextQuran_textviews.setTextSize(30f);
        TextQuran_textviews.setTypeface(font_besmellah);
        TextQuran_textviews.setText(value.besmellahe_alrahman_alrahim);

    }


}