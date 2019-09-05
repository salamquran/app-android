package com.ermile.salamquran.Function.FileManager;

import android.content.Context;
import android.util.Log;

import com.ermile.salamquran.Static.tag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadFromAsset {
    Context context;

    public String LoadFromAsset(Context context,String langName,String format, String CbarsetName) {
        this.context = context;
        try {
            InputStream file= context.getAssets().open(langName+format);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file, CbarsetName));
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
            }
            Log.d(tag.ac_Splash, "Load Json File: "+langName+format+" From Asset!");
            Log.d(tag.FileManager, "LoadFromAsset: Value: \n" +text.toString());
            bufferedReader.close();
            return text.toString();
        } catch (IOException ex) {
            Log.e(tag.FileManager, "LoadFromAsset: ",ex );
            Log.e(tag.ac_Splash, "LoadFromAsset: ",ex );
            Log.e(tag.error, "LoadFromAsset: ",ex );
            ex.printStackTrace();
            return null;
        }
    }
}
