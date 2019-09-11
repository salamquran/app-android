package com.ermile.salamquran.Actitvty;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.Item.itemQuran.ayat;
import com.ermile.salamquran.Item.itemQuran.word;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.tag;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ayat ayat = new ayat();
        addWord(ayat);

        Gson gson = new Gson();

        Log.d(tag.publicsh, "onCreate: "+gson.toJson(ayat));














    }

    private void addWord(ayat ayat){
        List<word> wordList = new ArrayList<>();
        List<ayat> ayatList = new ArrayList<>();
        SQLiteDatabase mydb = new MyDatabase(this).getWritableDatabase();
        Cursor pageData = mydb.rawQuery("SELECT * FROM quran_word WHERE page=1", null);
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
            int index = pageData.getInt(pageData.getColumnIndex("index"));

            wordList.add(new word(text));

            if (charType.equals("end")){
                ayat.setWords(wordList);
                ayat.notify();
            }


        }
        mydb.close();
        pageData.close();
    }

}
