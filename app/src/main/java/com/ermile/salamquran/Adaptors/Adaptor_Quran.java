package com.ermile.salamquran.Adaptors;

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

import com.ermile.salamquran.Functions.Download;
import com.ermile.salamquran.Functions.FileManager;
import com.ermile.salamquran.Functions.MyDatabase;
import com.ermile.salamquran.Functions.utility;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Value.QuranValue;
import com.ermile.salamquran.Value.file;
import com.ermile.salamquran.Value.format;
import com.ermile.salamquran.Value.statics;
import com.ermile.salamquran.Value.tag;
import com.ermile.salamquran.Value.url;

import java.io.File;
import java.util.Objects;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Adaptor_Quran extends androidx.viewpager.widget.PagerAdapter {

    private boolean hasFontOsmani = false;
    private LinearLayout linearLayout_Line = null;
    private onTochListener tochListener;

    public Adaptor_Quran(onTochListener tochListener) {
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
        hasFontOsmani = false;
        Context context = container.getContext();

        // Static Methods
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View view = Objects.requireNonNull(inflater).inflate(R.layout.item_quran , container , false);

        LinearLayout background_slide = view.findViewById(R.id.background_slide);
        final ImageView cutPage_R = view.findViewById(R.id.cutPage_R);
        final ImageView cutPage_L = view.findViewById(R.id.cutPage_L);
        final ImageView spacePage_L = view.findViewById(R.id.spacePage_L);
        final ImageView spacePage_R = view.findViewById(R.id.spacePage_R);
        final TextView Top_surah = view.findViewById(R.id.quranItemViewPager_titleSurah);
        final TextView Top_juz = view.findViewById(R.id.quranItemViewPager_titleJuz);
        final TextView bottom_numberPage = view.findViewById(R.id.quranItemViewPager_pageNumber);

        boolean pageInfoIsSet = false;



        if (utility.isOdd(position)){
            cutPage_R.setVisibility(View.VISIBLE);
            spacePage_L.setVisibility(View.VISIBLE);
        }
        else {
            cutPage_L.setVisibility(View.VISIBLE);
            spacePage_R.setVisibility(View.VISIBLE);
        }


        int testLine =0;
        int lineRender = 1;
        @SuppressLint("SdCardPath") Typeface font = Typeface.createFromFile("/data/user/0/com.ermile.salamquran/files/font_nabi.ttf");
        if (position > 0){
            if (FileManager.findFile_storage("/"+ file.font_OsmanTaha+"/","p"+position+ format.ttf)){
                File fontFile = FileManager.getFile_storage("/"+file.font_OsmanTaha+"/","p"+position+ format.ttf);
                try {
                    font = Typeface.createFromFile(fontFile.getPath());
                    hasFontOsmani = true;
                }catch (Exception e){
                    Log.e(tag.error, "QuranAdaptor: Set Downloaded Fot ",e );

                }

            }
            else {
                Download.Font(context, url.dlGithub_font_Quran_v1+"p"+position+format.ttf,file.font_OsmanTaha,"p"+ position);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) context.getResources().getDimension(R.dimen._30sdp));

            SQLiteDatabase mydb = new MyDatabase(context).getReadableDatabase();
            Cursor pageData = mydb.rawQuery("SELECT * FROM quran_word WHERE page="+position, null);
            int dbRenderCunt = 0;
            while (pageData.moveToNext()) {
                dbRenderCunt++;

                String code = pageData.getString(pageData.getColumnIndex("code"));
                String text = pageData.getString(pageData.getColumnIndex("text"));
                String char_type = pageData.getString(pageData.getColumnIndex("char_type"));
                final int aya = pageData.getInt(pageData.getColumnIndex("aya"));
                final int juz = pageData.getInt(pageData.getColumnIndex("juz"));
                final int sura = pageData.getInt(pageData.getColumnIndex("sura"));
                int line = pageData.getInt(pageData.getColumnIndex("line"));
                int page = pageData.getInt(pageData.getColumnIndex("page"));
                int index = pageData.getInt(pageData.getColumnIndex("index"));

                if (!pageInfoIsSet){
                    pageInfoIsSet = true;
                    Top_surah.setText(context.getString(R.string.surah)+ QuranValue.listSura[sura]);
                    Top_juz.setText(context.getString(R.string.juz)+ utility.numberToFarsi(juz));
                    bottom_numberPage.setText(utility.numberToFarsi(page));
                }


                if (line == 14 && dbRenderCunt == pageData.getCount()){
                    crateTitelVers(context,background_slide,sura+1);
                    lineRender++;
                    lineRender++;
                }

                if(testLine < line){
                    int t = line - lineRender;
                    switch (t){
                        case 2:
                            if (page != 1){
                                Log.e(tag.important, "Page: "+page +" TWO" );
                                lineRender = line+1;
                                crateTitelVers(context,background_slide,sura);
                                crateBesmellah(context,background_slide,index);
                            }
                            break;
                        case 1:
                            if (page == 1 || line == 1){
                                crateTitelVers(context,background_slide,sura);
                            }
                            else {
                                if (line == 2){
                                    Log.e(tag.important, "Page: "+page +" ONE > Besmellah" );
                                    crateBesmellah(context,background_slide,index);
                                    lineRender++;
                                    lineRender++;
                                }
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
                    crateWordQuran(context,background_slide,linearLayout_Line,font,text,code,index,aya,char_type);
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
        void wordOnLongclickListener(int indexAya);
    }


    @SuppressLint("SetTextI18n")
    private void crateWordQuran(Context context, final LinearLayout background_slide, LinearLayout linearLayout_Line , Typeface font, String text , String code, final int index , int aya, String type ){
        TextView wordQuran = new TextView(context);
        wordQuran.setTextColor(Color.parseColor("#000000"));
        wordQuran.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        wordQuran.setGravity(View.TEXT_ALIGNMENT_CENTER);
        linearLayout_Line.addView(wordQuran);
        wordQuran.setTag(index);

        wordQuran.setTypeface(font);
        if (hasFontOsmani){
            wordQuran.setTextSize(context.getResources().getDimension(R.dimen._9ssp));
            wordQuran.setText(Html.fromHtml(code).toString());
        }else {
            wordQuran.setTextSize(context.getResources().getDimension(R.dimen._5ssp));
            if ("end".equals(type)) {
                wordQuran.setText(" ( " + utility.numberToFarsi(aya) + " ) ");
            } else {
                wordQuran.setText(" " + text + " ");
            }
        }
        wordQuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tochListener.wordOnclickListener();
            }
        });

    }

    private void crateTitelVers(Context context , LinearLayout background_slide,int vars ){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) context.getResources().getDimension(R.dimen._30sdp));
        layoutParams.topMargin = (int) context.getResources().getDimension(R.dimen._3sdp);
        layoutParams.bottomMargin = (int) context.getResources().getDimension(R.dimen._3sdp);
        layoutParams.setMarginStart((int) context.getResources().getDimension(R.dimen._9sdp));
        layoutParams.setMarginEnd((int) context.getResources().getDimension(R.dimen._9sdp));

        LinearLayout LinearLayout_headerSura = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_header_sura,null);
        LinearLayout_headerSura.setLayoutParams(layoutParams);
        background_slide.addView(LinearLayout_headerSura);

        TextView textView = LinearLayout_headerSura.findViewById(R.id.aa);
        textView.setText(QuranValue.listSura[vars]);
        textView.setTag(vars+"Title");

    }
    private void crateBesmellah(Context context , LinearLayout background_slide,int index ){
        @SuppressLint("SdCardPath") Typeface font_besmellah = Typeface.createFromFile("/data/user/0/com.ermile.salamquran/files/bismillah.ttf");
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

        TextQuran_textviews.setTextSize(context.getResources().getDimension(R.dimen._12ssp));
        TextQuran_textviews.setTypeface(font_besmellah);
        TextQuran_textviews.setText(statics.besmellahe_alrahman_alrahim);

    }


}